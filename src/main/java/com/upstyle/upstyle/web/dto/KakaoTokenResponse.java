package com.upstyle.upstyle.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoTokenResponse {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private int refresh_token_expires_in;
    private String id_token;
}
