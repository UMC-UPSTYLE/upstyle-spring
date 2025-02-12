package com.upstyle.upstyle.web.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;

@Getter
@NoArgsConstructor //역직렬화를 위한 기본 생성자
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoUserInfoResponseDto {

    //회원 번호
    @JsonProperty("id")
    public Long id;

    //서비스에 연결 완료된 시각. UTC
    @JsonProperty("connected_at")
    public Date connectedAt;


    //카카오 계정 정보
    @JsonProperty("kakao_account")
    public KakaoAccount kakaoAccount;

    @Getter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class KakaoAccount {

        //닉네임 제공 동의 여부
        @JsonProperty("profile_nickname_needs_agreement")
        public Boolean isNickNameAgree;

        //사용자 프로필 정보
        @JsonProperty("profile")
        public Profile profile;

        //이메일 제공 동의 여부
        @JsonProperty("email_needs_agreement")
        public Boolean isEmailAgree;

        //이메일이 유효 여부
        // true : 유효한 이메일, false : 이메일이 다른 카카오 계정에 사용돼 만료
        @JsonProperty("is_email_valid")
        public Boolean isEmailValid;

        //이메일이 인증 여부
        //true : 인증된 이메일, false : 인증되지 않은 이메일
        @JsonProperty("is_email_verified")
        public Boolean isEmailVerified;

        //카카오계정 대표 이메일
        @JsonProperty("email")
        public String email;

        @Getter
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public class Profile {

            //닉네임
            @JsonProperty("nickname")
            public String nickName;


        }
    }


}

