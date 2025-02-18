package com.upstyle.upstyle.service.VoteService;

import com.upstyle.upstyle.domain.Vote;
import com.upstyle.upstyle.web.dto.VoteRequestDTO;
import com.upstyle.upstyle.web.dto.VoteResponseDTO;

public interface VoteCommandService {
    Vote addVote(Long userId, VoteRequestDTO.AddVoteDTO voteRequestDTO);

    VoteResponseDTO.ResponseVoteResultDTO responseVote(Long userId, Long voteId, VoteRequestDTO.ResponseVoteDTO responseVoteDTO);
}
