package com.upstyle.upstyle.service.VoteService;

import com.upstyle.upstyle.converter.ClothConverter;
import com.upstyle.upstyle.converter.VoteConverter;
import com.upstyle.upstyle.domain.Cloth;
import com.upstyle.upstyle.domain.Vote;
import com.upstyle.upstyle.repository.ClothRepository;
import com.upstyle.upstyle.repository.VoteRepository;
import com.upstyle.upstyle.web.dto.ClothResponseDTO;
import com.upstyle.upstyle.web.dto.VoteResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoteQueryServiceImpl implements VoteQueryService{

    private final VoteRepository voteRepository;

    @Override
    public VoteResponseDTO.VotePreviewListDTO getVotePreviewList(int page, int size) {
        Page<Vote> votePage = voteRepository.findPagedVotes(PageRequest.of(page, size));
        return VoteConverter.toVotePreviewListDTO(votePage);
    }
}
