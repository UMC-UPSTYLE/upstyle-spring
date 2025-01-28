package com.upstyle.upstyle.service.OotdService;

import com.upstyle.upstyle.apiPayload.code.status.ErrorStatus;
import com.upstyle.upstyle.apiPayload.exception.handler.OotdHandler;
import com.upstyle.upstyle.converter.OotdConverter;
import com.upstyle.upstyle.domain.Ootd;
import com.upstyle.upstyle.repository.ClothRepository;
import com.upstyle.upstyle.repository.OotdRepository;
import com.upstyle.upstyle.web.dto.OotdResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OotdQueryServiceImpl implements OotdQueryService{
    private final OotdRepository ootdRepository;

    @Override
    @Transactional
    public OotdResponseDTO.CalendarResponseDTO getCalendarResponseDTO(Long userId, int year, int month){
        List<Object[]> results = ootdRepository.findAllByUserIdAndYearAndMonth(userId,year,month);

        List<OotdResponseDTO.OotdPreviewDTO> dateOotdDTOList = results.stream()
                .map (result -> new OotdResponseDTO.OotdPreviewDTO(
                        (Long) result[0],  // id
                        (LocalDate) result[1], //date
                        (String) result[2] // imageUrl
                ))
                .collect(Collectors.toList());

        return OotdConverter.toCalendarResponseDTO(userId,dateOotdDTOList);
    }

    @Override
    @Transactional
    public OotdResponseDTO.OotdDTO getOotdById(Long ootdId){
        Ootd ootdWithClothList = ootdRepository.findWithClothList(ootdId)
                .orElseThrow(() -> new OotdHandler(ErrorStatus.OOTD_NOT_FOUND));

        Ootd ootdWithImageList = ootdRepository.findWithImageList(ootdId)
                .orElseThrow(() -> new OotdHandler(ErrorStatus.OOTD_NOT_FOUND));

        ootdWithClothList.setOotdImageList(ootdWithImageList.getOotdImageList());

        return OotdConverter.toOotdDTO(ootdWithClothList);
    }
}
