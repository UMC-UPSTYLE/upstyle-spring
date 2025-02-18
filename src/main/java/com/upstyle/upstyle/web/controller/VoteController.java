package com.upstyle.upstyle.web.controller;


import com.upstyle.upstyle.apiPayload.ApiResponse;
import com.upstyle.upstyle.converter.VoteConverter;
import com.upstyle.upstyle.domain.Vote;
import com.upstyle.upstyle.service.TokenService;
import com.upstyle.upstyle.service.VoteService.VoteCommandService;
import com.upstyle.upstyle.service.VoteService.VoteQueryService;
import com.upstyle.upstyle.web.dto.OotdResponseDTO;
import com.upstyle.upstyle.web.dto.VoteRequestDTO;
import com.upstyle.upstyle.web.dto.VoteResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
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
    private final TokenService tokenService;

    @PostMapping(value = "/", consumes = "application/json")
    @Operation(summary = "투표 글 작성 API")
    public ApiResponse<VoteResponseDTO.VoteDTO> createVote(
            HttpServletRequest request,
            @RequestBody @Valid VoteRequestDTO.AddVoteDTO voteRequestDTO)
    {
        String token = request.getHeader("Authorization");

        token = token.substring(7); // ✅ "Bearer " 제거
        Long userId = tokenService.getId(token); // ✅ JWT에서 userId 추출

        Vote vote = voteCommandService.addVote(userId, voteRequestDTO); // ✅ userId 전달
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
    public ApiResponse<VoteResponseDTO.ResponseVoteResultDTO> responseVote(
            HttpServletRequest request,
            @PathVariable Long voteId,
            @RequestBody @Valid VoteRequestDTO.ResponseVoteDTO responseVoteDTO)
    {
        String token = request.getHeader("Authorization");

        token = token.substring(7); // "Bearer " 제거
        Long userId = tokenService.getId(token); // JWT에서 userId 추출

        VoteResponseDTO.ResponseVoteResultDTO resultDTO = voteCommandService.responseVote(userId, voteId, responseVoteDTO); // ✅ userId 전달
        return ApiResponse.onSuccess(resultDTO);
    }
}
