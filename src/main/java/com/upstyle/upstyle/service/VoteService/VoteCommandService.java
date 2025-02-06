package com.upstyle.upstyle.service.VoteService;

import com.upstyle.upstyle.domain.Vote;
import com.upstyle.upstyle.web.dto.VoteRequestDTO;

public interface VoteCommandService {
    Vote addVote(VoteRequestDTO.AddVoteDTO voteRequestDTO);
}
