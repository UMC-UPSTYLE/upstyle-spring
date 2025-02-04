package com.upstyle.upstyle.converter;

import com.upstyle.upstyle.domain.User;
import com.upstyle.upstyle.domain.Vote;
import com.upstyle.upstyle.domain.VoteOption;
import com.upstyle.upstyle.web.dto.VoteRequestDTO;
import com.upstyle.upstyle.web.dto.VoteResponseDTO;

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
                .userId(vote.getUser().getId())
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
                .build();
    }
}
