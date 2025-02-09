package com.upstyle.upstyle.service.BookmarkService;

import com.upstyle.upstyle.apiPayload.code.status.ErrorStatus;
import com.upstyle.upstyle.apiPayload.exception.handler.ClothHandler;
import com.upstyle.upstyle.apiPayload.exception.handler.UserHandler;
import com.upstyle.upstyle.converter.BookmarkConverter;
import com.upstyle.upstyle.domain.Cloth;
import com.upstyle.upstyle.domain.Ootd;
import com.upstyle.upstyle.domain.User;
import com.upstyle.upstyle.domain.mapping.ClothBookmark;
import com.upstyle.upstyle.repository.BookmarkRepository;
import com.upstyle.upstyle.repository.ClothRepository;
import com.upstyle.upstyle.repository.OotdRepository;
import com.upstyle.upstyle.repository.UserRepository;
import com.upstyle.upstyle.service.BookmarkService.BookmarkCommandService;
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
    private final OotdRepository ootdRepository;

    @Override
    @Transactional
    public BookmarkResponseDTO.AddBookmarkResultDTO addBookmark(BookmarkRequestDTO.AddBookmarkDTO bookmarkRequest) {
        User user = userRepository.findById(bookmarkRequest.getUserId())
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        Cloth cloth = clothRepository.findById(bookmarkRequest.getClothId())
                .orElseThrow(() -> new ClothHandler(ErrorStatus.CLOTH_ID_NOT_FOUND));

        // 이미 북마크되어 있는지 확인
        ClothBookmark existingBookmark = bookmarkRepository.findByUserIdAndClothId(bookmarkRequest.getUserId(), bookmarkRequest.getClothId());

        if (existingBookmark != null) {
            // 이미 북마크되어 있으면 삭제
            bookmarkRepository.delete(existingBookmark);
            return BookmarkResponseDTO.AddBookmarkResultDTO.builder()
                    .id(existingBookmark.getId())
                    .userId(user.getId())
                    .clothId(cloth.getId())
                    .isBookmarked(false)
                    .build();  // 삭제 후 정보 반환
        }

        // 가장 최근 OOTD 찾기
        Ootd latestOotd = ootdRepository.findLatestOotdByClothId(bookmarkRequest.getClothId());

        ClothBookmark clothBookmark = ClothBookmark.builder()
                .user(user)
                .cloth(cloth)
                .ootdId(latestOotd.getId())
                .ootdImageUrl(latestOotd.getOotdImageList().isEmpty() ? null : latestOotd.getOotdImageList().get(0).getImageUrl())
                .build();

        bookmarkRepository.save(clothBookmark);

        return BookmarkConverter.toBookmarkResponseDTO(clothBookmark);
    }

}
