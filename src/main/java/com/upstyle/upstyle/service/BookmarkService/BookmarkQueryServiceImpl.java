package com.upstyle.upstyle.service.BookmarkService;

import com.upstyle.upstyle.apiPayload.code.status.ErrorStatus;
import com.upstyle.upstyle.apiPayload.exception.handler.ClothHandler;
import com.upstyle.upstyle.apiPayload.exception.handler.UserHandler;
import com.upstyle.upstyle.converter.BookmarkConverter;
import com.upstyle.upstyle.domain.Cloth;
import com.upstyle.upstyle.domain.User;
import com.upstyle.upstyle.domain.mapping.ClothBookmark;
import com.upstyle.upstyle.repository.BookmarkRepository;
import com.upstyle.upstyle.repository.ClothKindRepository;
import com.upstyle.upstyle.repository.UserRepository;
import com.upstyle.upstyle.web.dto.BookmarkResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkQueryServiceImpl implements BookmarkQueryService{
    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;
    private final ClothKindRepository clothKindRepository;

    @Override
    public BookmarkResponseDTO.BookmarkListDTO getBookmarkedClothes(Long userId, Long kindId, int page, int size) {
        // 유저 존재 여부 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        // kindId가 있을 경우 존재 여부 확인
        if (kindId != null) {
            clothKindRepository.findById(kindId)
                    .orElseThrow(() -> new ClothHandler(ErrorStatus.CLOTH_KIND_NOT_FOUND));
        }
        PageRequest pageable = PageRequest.of(page, size);
        Page<ClothBookmark> bookmarkPage = bookmarkRepository.findBookmarkedClothes(userId, kindId, pageable);
        return BookmarkConverter.toBookmarkListDTO(bookmarkPage);
    }
}
