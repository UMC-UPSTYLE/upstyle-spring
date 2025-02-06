package com.upstyle.upstyle.service.VoteService;

import com.upstyle.upstyle.domain.Vote;
import com.upstyle.upstyle.web.dto.VoteRequestDTO;
import com.upstyle.upstyle.web.dto.VoteResponseDTO;

public interface VoteCommandService {
    Vote addVote(VoteRequestDTO.AddVoteDTO voteRequestDTO);

    VoteResponseDTO.ResponseVoteResultDTO responseVote(Long voteId, VoteRequestDTO.ResponseVoteDTO responseVoteDTO);
}
