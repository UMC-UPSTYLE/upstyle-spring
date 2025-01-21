package com.upstyle.upstyle.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.upstyle.upstyle.domain.User;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.upstyle.upstyle.domain.enums.Role;
import com.upstyle.upstyle.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class GoolgeAuthService {

    private final UserRepository userRepository;

    private static final String CLIENT_ID = "698248660337qiq5orai985jarf8gbgr0tl13hk1b0sb.apps.googleusercontent.com";

    public GoogleIdToken.Payload verifyToken(String idTokenString) throws Exception {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance() // JacksonFactory 사용
        )
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();

        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken != null) {
            return idToken.getPayload();
        } else {
            throw new RuntimeException("Invalid ID token.");
        }
    }

    public User verifyAndProcessToken(String idToken) {
        try {
            GoogleIdToken.Payload payload = verifyToken(idToken);
            String email = payload.getEmail();
            String name = (String) payload.get("name");

            // 사용자 생성 또는 조회
            return userRepository.findByEmail(email)
                    .orElseGet(() -> userRepository.save(
                            User.builder()
                                    .email(email)
                                    .nickname(name)
                                    .role(Role.USER)
                                    .build()
                    ));
        } catch (Exception e) {
            throw new RuntimeException("Token verification failed", e);
        }
    }

}
