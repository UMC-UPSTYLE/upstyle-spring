package com.upstyle.upstyle.service;

import com.upstyle.upstyle.domain.Ootd;
import com.upstyle.upstyle.domain.User;
import com.upstyle.upstyle.repository.ClothRepository;
import com.upstyle.upstyle.repository.OotdRepository;
import com.upstyle.upstyle.repository.UserRepository;
import com.upstyle.upstyle.web.dto.OotdRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OotdCommandServiceImpl implements OotdCommandService {
    private final OotdRepository ootdRepository;
    private final ClothRepository clothRepository;
    private final UserRepository userRepository;
    @Override
    public Ootd addOotd(OotdRequestDTO.addOotdDTO request){
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid User ID"));

    }
}
