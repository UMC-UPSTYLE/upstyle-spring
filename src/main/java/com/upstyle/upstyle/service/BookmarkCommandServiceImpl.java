package com.upstyle.upstyle.service;

import com.upstyle.upstyle.apiPayload.code.status.ErrorStatus;
import com.upstyle.upstyle.apiPayload.exception.handler.ClothHandler;
import com.upstyle.upstyle.apiPayload.exception.handler.UserHandler;
import com.upstyle.upstyle.converter.BookmarkConverter;
import com.upstyle.upstyle.converter.ClosetConverter;
import com.upstyle.upstyle.domain.Cloth;
import com.upstyle.upstyle.domain.User;
import com.upstyle.upstyle.domain.mapping.ClothBookmark;
import com.upstyle.upstyle.repository.BookmarkRepository;
import com.upstyle.upstyle.repository.ClothRepository;
import com.upstyle.upstyle.repository.UserRepository;
import com.upstyle.upstyle.web.dto.BookmarkRequestDTO;
import com.upstyle.upstyle.web.dto.BookmarkResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookmarkCommandServiceImpl implements BookmarkCommandService {
    private final UserRepository userRepository;
    private final BookmarkRepository bookmarkRepository;
    private final ClothRepository clothRepository;

    @Override
    @Transactional
    public BookmarkResponseDTO addBookmark(BookmarkRequestDTO bookmarkRequest) {
        User user = userRepository.findById(bookmarkRequest.getUserId())
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        Cloth cloth = clothRepository.findById(bookmarkRequest.getClothId())
                .orElseThrow(() -> new ClothHandler(ErrorStatus.CLOTH_ID_NOT_FOUND));

        ClothBookmark clothBookmark = ClothBookmark.builder()
                .user(user)
                .cloth(cloth)
                .build();

        bookmarkRepository.save(clothBookmark);

        return BookmarkConverter.toBookmarkResponseDTO(clothBookmark);
    }

}
