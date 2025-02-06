package com.upstyle.upstyle.web.controller;


import com.upstyle.upstyle.apiPayload.ApiResponse;
import com.upstyle.upstyle.converter.VoteConverter;
import com.upstyle.upstyle.domain.Vote;
import com.upstyle.upstyle.service.VoteService.VoteCommandService;
import com.upstyle.upstyle.service.VoteService.VoteQueryService;
import com.upstyle.upstyle.web.dto.OotdResponseDTO;
import com.upstyle.upstyle.web.dto.VoteRequestDTO;
import com.upstyle.upstyle.web.dto.VoteResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/votes")
@RequiredArgsConstructor
@Validated
public class VoteController {

    private final VoteCommandService voteCommandService;
    private final VoteQueryService voteQueryService;

    @PostMapping(value = "/", consumes = "application/json")
    @Operation(summary = "투표 글 작성 API")
    public ApiResponse<VoteResponseDTO.VoteDTO> createVote(@RequestBody VoteRequestDTO.AddVoteDTO voteRequestDTO) {
        Vote vote = voteCommandService.addVote(voteRequestDTO);
        return ApiResponse.onSuccess(VoteConverter.toVoteDTO(vote));
    }

    @GetMapping(value = "/")
    @Operation(summary = "투표 글 미리보기 리스트 조회 API")
    public ApiResponse<VoteResponseDTO.VotePreviewListDTO> getVotePreviewList(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                                                   @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
            VoteResponseDTO.VotePreviewListDTO previewList = voteQueryService.getVotePreviewList(page, size);
            return ApiResponse.onSuccess(previewList);
    }

    @GetMapping("/{voteId}")
    @Operation(summary = "투표 글 상세 조회 API")
    public ApiResponse<VoteResponseDTO.VoteDTO> getVote(@PathVariable Long voteId){
        VoteResponseDTO.VoteDTO voteDTO = voteQueryService.getVoteById(voteId);
        return ApiResponse.onSuccess(voteDTO);
    }

    @PostMapping(value = "/{voteId}/response", consumes = "application/json")
    @Operation(summary = "투표 응답 API")
    public ApiResponse<VoteResponseDTO.ResponseVoteResultDTO> responseVote(@PathVariable Long voteId, @RequestBody VoteRequestDTO.ResponseVoteDTO responseVoteDTO) {

        VoteResponseDTO.ResponseVoteResultDTO resultDTO = voteCommandService.responseVote(voteId, responseVoteDTO);

        return ApiResponse.onSuccess(resultDTO);
    }
}
