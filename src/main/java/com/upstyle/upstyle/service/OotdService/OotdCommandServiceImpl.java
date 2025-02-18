package com.upstyle.upstyle.service.OotdService;

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
import org.springframework.data.jpa.repository.JpaRepository;
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
    public Ootd addOotd(Long userId, OotdRequestDTO.addOotdDTO ootdRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        // OOTD 생성 및 저장
        Ootd newOotd = OotdConverter.toOotd(ootdRequest, user);
        ootdRepository.save(newOotd);

        // OOTD-Cloth 연결
        List<OotdCloth> ootdClothList = ootdRequest.getClothRequestDTOList().stream()
                .map(request -> {
                    Cloth cloth = (request.getClothId() == 0) ? createNewCloth(request, user) : fetchExistingCloth(request.getClothId());
                    return createAndSaveOotdCloth(newOotd, cloth);
                }).collect(Collectors.toList());

        newOotd.setOotdClothList(ootdClothList);

        // OOTD-Image 저장
        List<OotdImage> ootdImageList = ootdRequest.getImageUrls().stream()
                .map(url -> OotdConverter.toOotdImage(url, newOotd))
                .collect(Collectors.toList());

        ootdImageRepository.saveAll(ootdImageList);
        newOotd.setOotdImageList(ootdImageList);

        return ootdRepository.save(newOotd);
    }

    private Cloth createNewCloth(OotdRequestDTO.ClothRequestDTO request, User user) {
        Cloth newCloth = OotdConverter.toCloth(request, user);

        newCloth.setCategory(fetchEntity(request.getClothCategoryId(), clothCategoryRepository, ErrorStatus.CLOTH_CATEGORY_NOT_FOUND));
        newCloth.setFit(fetchEntity(request.getFitCategoryId(), clothFitRepository, ErrorStatus.CLOTH_FIT_NOT_FOUND));
        newCloth.setColor(fetchEntity(request.getColorCategoryId(), clothColorRepository, ErrorStatus.CLOTH_COLOR_NOT_FOUND));
        newCloth.setKind(fetchEntity(request.getClothKindId(), clothKindRepository, ErrorStatus.CLOTH_KIND_NOT_FOUND));

        return clothRepository.save(newCloth);
    }

    private Cloth fetchExistingCloth(Long clothId) {
        return clothRepository.findById(clothId)
                .orElseThrow(() -> new ClothHandler(ErrorStatus.CLOTH_ID_NOT_FOUND));
    }

    private <T> T fetchEntity(Long id, JpaRepository<T, Long> repository, ErrorStatus error) {
        return repository.findById(id).orElseThrow(() -> new ClothHandler(error));
    }

    private OotdCloth createAndSaveOotdCloth(Ootd ootd, Cloth cloth) {
        OotdCloth ootdCloth = new OotdCloth();
        ootdCloth.setOotd(ootd);
        ootdCloth.setCloth(cloth);
        return ootdClothRepository.save(ootdCloth);
    }
}
