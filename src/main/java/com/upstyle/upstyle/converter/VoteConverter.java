package com.upstyle.upstyle.converter;

import com.upstyle.upstyle.domain.User;
import com.upstyle.upstyle.domain.Vote;
import com.upstyle.upstyle.domain.VoteOption;
import com.upstyle.upstyle.web.dto.VoteRequestDTO;
import com.upstyle.upstyle.web.dto.VoteResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class VoteConverter {

    // VoteRequestDTO.UploadVoteDTO -> Vote 엔티티 변환
    public static Vote toVoteEntity(VoteRequestDTO.AddVoteDTO request, User user) {
        Vote vote = new Vote();
        vote.setUser(user);
        vote.setTitle(request.getTitle());
        vote.setBody(request.getBody());
        return vote;
    }

    // VoteOptionRequest -> VoteOption 엔티티 변환
    public static VoteOption toVoteOptionEntity(VoteRequestDTO.VoteOptionDTO optionDTO, Vote vote) {
        VoteOption voteOption = new VoteOption();
        voteOption.setVote(vote);
        voteOption.setImageUrl(optionDTO.getImageUrl());
        voteOption.setName(optionDTO.getName());
        voteOption.setClothId(optionDTO.getClothId());
        return voteOption;
    }

    // Vote 엔티티 -> VoteResponseDTO 변환
    public static VoteResponseDTO.VoteDTO toVoteDTO(Vote vote) {
        List<VoteResponseDTO.VoteOptionDTO> optionDTOs = vote.getOptionList().stream()
                .map(VoteConverter::toVoteOptionDTO)
                .collect(Collectors.toList());

        return VoteResponseDTO.VoteDTO.builder()
                .id(vote.getId())
                .title(vote.getTitle())
                .body(vote.getBody())
                .user(VoteResponseDTO.User.builder()
                        .id(vote.getUser().getId())
                        .nickname(vote.getUser().getNickname())
                        .build())
                .optionList(optionDTOs)
                .build();
    }

    // VoteOption 엔티티 -> VoteOptionDTO 변환
    public static VoteResponseDTO.VoteOptionDTO toVoteOptionDTO(VoteOption voteOption) {
        return VoteResponseDTO.VoteOptionDTO.builder()
                .id(voteOption.getId())
                .imageUrl(voteOption.getImageUrl())
                .name(voteOption.getName())
                .clothId(voteOption.getClothId())
                .responseCount(voteOption.getResponseCount())
                .build();
    }

    // VotePreviewDTO 변환
    public static VoteResponseDTO.VotePreviewDTO toVotePreviewDTO(Vote vote) {
        // 각 VoteOption의 responseCount 값을 합산
        int totalResponseCount = vote.getOptionList().stream()
                .mapToInt(VoteOption::getResponseCount)
                .sum();

        return VoteResponseDTO.VotePreviewDTO.builder()
                .id(vote.getId())
                .title(vote.getTitle())
                .totalResponseCount(totalResponseCount)
                .build();
    }

    // VotePreviewListDTO 변환
    public static VoteResponseDTO.VotePreviewListDTO toVotePreviewListDTO(Page<Vote> votePage) {
        List<VoteResponseDTO.VotePreviewDTO> votePreviewList = votePage.stream()
                .map(VoteConverter::toVotePreviewDTO)
                .collect(Collectors.toList());

        return VoteResponseDTO.VotePreviewListDTO.builder()
                .votePreviewList(votePreviewList)
                .listSize(votePreviewList.size())
                .totalPage(votePage.getTotalPages())
                .totalElements(votePage.getTotalElements())
                .isFirst(votePage.isFirst())
                .isLast(votePage.isLast())
                .build();
    }
}
