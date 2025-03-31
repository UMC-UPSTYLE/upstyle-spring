package com.upstyle.upstyle.service.VoteService;


import com.upstyle.upstyle.apiPayload.code.status.ErrorStatus;
import com.upstyle.upstyle.apiPayload.exception.handler.UserHandler;
import com.upstyle.upstyle.apiPayload.exception.handler.VoteHandler;
import com.upstyle.upstyle.converter.VoteConverter;
import com.upstyle.upstyle.domain.User;
import com.upstyle.upstyle.domain.Vote;
import com.upstyle.upstyle.domain.VoteOption;
import com.upstyle.upstyle.repository.UserRepository;
import com.upstyle.upstyle.repository.VoteOptionRepository;
import com.upstyle.upstyle.repository.VoteRepository;
import com.upstyle.upstyle.web.dto.VoteRequestDTO;
import com.upstyle.upstyle.web.dto.VoteResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoteCommandServiceImpl implements VoteCommandService{

    private final VoteRepository voteRepository;
    private final VoteOptionRepository voteOptionRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Vote addVote(Long userId, VoteRequestDTO.AddVoteDTO voteRequestDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        // Vote 엔티티 생성 및 저장
        Vote vote = new Vote();
        vote.setUser(user);
        vote.setTitle(voteRequestDTO.getTitle());
        vote.setBody(voteRequestDTO.getBody());
        vote.setImageUrl(voteRequestDTO.getImageUrl());
        vote = voteRepository.save(vote);  // 저장 후 업데이트된 객체 받기

        // VoteOption 저장 로직
        for (VoteRequestDTO.AddVoteOptionDTO option : voteRequestDTO.getOptionList()) {
            VoteOption voteOption = new VoteOption();
            voteOption.setImageUrl(option.getImageUrl());
            voteOption.setName(option.getName());
            voteOption.setClothId(option.getClothId());
            voteOption.setResponseCount(0);
            voteOption.setVote(vote);  // 외래 키 설정
            voteOptionRepository.save(voteOption);
        }

        return vote;
    }

    @Override
    @Transactional
    public VoteResponseDTO.ResponseVoteResultDTO responseVote(Long userId, Long voteId, VoteRequestDTO.ResponseVoteDTO responseVoteDTO) {
        // User 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        // VoteOption 조회
        VoteOption voteOption = voteOptionRepository.findById(responseVoteDTO.getOptionId())
                .orElseThrow(() -> new VoteHandler(ErrorStatus.VOTE_OPTION_NOT_FOUND));

        // optionId가 voteId에 속해 있는지 검증
        if (!voteOption.getVote().getId().equals(voteId)) {
            throw new VoteHandler(ErrorStatus.VOTE_OPTION_MISMATCH);
        }

        // 응답 수 증가 및 저장
        voteOption.setResponseCount(voteOption.getResponseCount() + 1);
        voteOptionRepository.save(voteOption);

        // 응답 결과 리스트 반환
        List<VoteOption> options = voteOptionRepository.findByVoteId(voteOption.getVote().getId());
        return VoteConverter.toResponseVoteResultDTO(options);
    }
}
