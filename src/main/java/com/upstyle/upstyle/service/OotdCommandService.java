package com.upstyle.upstyle.service;

import com.upstyle.upstyle.domain.Ootd;
import com.upstyle.upstyle.web.dto.OotdRequestDTO;

public interface OotdCommandService {
    Ootd addOotd(OotdRequestDTO.addOotdDTO request);
}
