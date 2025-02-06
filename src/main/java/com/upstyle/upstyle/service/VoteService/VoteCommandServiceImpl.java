package com.upstyle.upstyle.service.VoteService;


import com.upstyle.upstyle.apiPayload.code.status.ErrorStatus;
import com.upstyle.upstyle.apiPayload.exception.handler.UserHandler;
import com.upstyle.upstyle.domain.User;
import com.upstyle.upstyle.domain.Vote;
import com.upstyle.upstyle.domain.VoteOption;
import com.upstyle.upstyle.repository.UserRepository;
import com.upstyle.upstyle.repository.VoteOptionRepository;
import com.upstyle.upstyle.repository.VoteRepository;
import com.upstyle.upstyle.web.dto.VoteRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VoteCommandServiceImpl implements VoteCommandService{

    private final VoteRepository voteRepository;
    private final VoteOptionRepository voteOptionRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Vote addVote(VoteRequestDTO.AddVoteDTO voteRequestDTO) {
        User user = userRepository.findById(voteRequestDTO.getUserId())
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        // Vote 엔티티 생성 및 저장
        Vote vote = new Vote();
        vote.setUser(user);
        vote.setTitle(voteRequestDTO.getTitle());
        vote.setBody(voteRequestDTO.getBody());
        vote = voteRepository.save(vote);  // 저장 후 업데이트된 객체 받기

        // VoteOption 저장 로직
        for (VoteRequestDTO.VoteOptionDTO option : voteRequestDTO.getOptionList()) {
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
}
