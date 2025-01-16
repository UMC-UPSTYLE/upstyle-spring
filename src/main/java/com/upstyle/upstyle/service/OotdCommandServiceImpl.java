package com.upstyle.upstyle.service;

import com.upstyle.upstyle.apiPayload.code.status.ErrorStatus;
import com.upstyle.upstyle.apiPayload.exception.handler.ClothHandler;
import com.upstyle.upstyle.apiPayload.exception.handler.UserHandler;
import com.upstyle.upstyle.aws.s3.AmazonS3Manager;
import com.upstyle.upstyle.converter.OotdConverter;
import com.upstyle.upstyle.domain.*;
import com.upstyle.upstyle.domain.mapping.OotdCloth;
import com.upstyle.upstyle.repository.*;
import com.upstyle.upstyle.web.dto.OotdRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OotdCommandServiceImpl implements OotdCommandService {

    private final ClothKindRepository clothKindRepository;
    private final OotdRepository ootdRepository;
    private final OotdImageRepository ootdImageRepository;
    private final ClothRepository clothRepository;
    private final UserRepository userRepository;
    private final ClothCategoryRepository clothCategoryRepository;
    private final ClothFitRepository clothFitRepository;
    private final ClothColorRepository clothColorRepository;
    private final OotdClothRepository ootdClothRepository;
    private final UuidRepository uuidRepository;
    private final AmazonS3Manager s3Manager;


    @Override
    @Transactional
    public Ootd addOotd(OotdRequestDTO.addOotdDTO ootdRequest, MultipartFile[] ootdImages){
        User user = userRepository.findById(ootdRequest.getUserId())
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        Ootd newOotd = OotdConverter.toOotd(ootdRequest,user);

        List<OotdCloth> OotdclothList = ootdRequest.getClothRequestDTOList().stream()
                .map(ClothRequest -> {
                    //OotdConverter로 ClothRequestDTO -> Cloth 엔티티로 변환.
                    Cloth newCloth = OotdConverter.toCloth(ClothRequest, user);

                    //ClothRequestDTO로 받은 Cloth의 각 속성 id, 레포지토리에서 찾아 Cloth 엔티티에 set.
                    ClothCategory category = clothCategoryRepository.findById(ClothRequest.getClothCategoryId())
                            .orElseThrow(() -> new ClothHandler(ErrorStatus.CLOTH_CATEGORY_NOT_FOUND));
                    newCloth.setCategory(category);

                    ClothFit fit = clothFitRepository.findById(ClothRequest.getFitCategoryId())
                            .orElseThrow(() -> new ClothHandler(ErrorStatus.CLOTH_FIT_NOT_FOUND));
                    newCloth.setFit(fit);

                    ClothColor color = clothColorRepository.findById(ClothRequest.getColorCategoryId())
                            .orElseThrow(() -> new ClothHandler(ErrorStatus.CLOTH_COLOR_NOT_FOUND));
                    newCloth.setColor(color);

                    ClothKind kind = clothKindRepository.findById(ClothRequest.getClothKindId())
                            .orElseThrow(()-> new ClothHandler((ErrorStatus.CLOTH_KIND_NOT_FOUND)));
                    newCloth.setKind(kind);

                    clothRepository.save(newCloth);

                    //ootd와 Cloth 매핑 설정
                    OotdCloth NewOotdCloth = new OotdCloth();
                    NewOotdCloth.setOotd(newOotd);
                    NewOotdCloth.setCloth(newCloth);
                    ootdClothRepository.save(NewOotdCloth);

                    return NewOotdCloth;
                }).collect(Collectors.toList());

        newOotd.setOotdClothList(OotdclothList);
        ootdRepository.save(newOotd);

        // 다중 이미지 업로드 처리
        for (MultipartFile ootdImage : ootdImages) {
            String uuid = UUID.randomUUID().toString();
            Uuid savedUuid = uuidRepository.save(Uuid.builder().uuid(uuid).build());
            String imageUrl = s3Manager.uploadFile(s3Manager.generateOotdKeyName(savedUuid), ootdImage);

            OotdImage ootdImageEntity = OotdConverter.toOotdImage(imageUrl, newOotd);
            ootdImageRepository.save(ootdImageEntity);
        }

        return ootdRepository.save(newOotd);

    }
}
