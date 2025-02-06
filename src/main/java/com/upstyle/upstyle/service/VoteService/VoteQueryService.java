package com.upstyle.upstyle.service.VoteService;

import com.upstyle.upstyle.web.dto.ClothResponseDTO;
import com.upstyle.upstyle.web.dto.VoteResponseDTO;

import java.util.List;

public interface VoteQueryService {
    VoteResponseDTO.VotePreviewListDTO getVotePreviewList(int page, int size);
}
