package com.upstyle.upstyle.service;

import com.upstyle.upstyle.domain.Ootd;
import com.upstyle.upstyle.web.dto.OotdRequestDTO;
import org.springframework.web.multipart.MultipartFile;

public interface OotdCommandService {
    Ootd addOotd(OotdRequestDTO.addOotdDTO ootdRequest, MultipartFile[] ootdImages);
}
