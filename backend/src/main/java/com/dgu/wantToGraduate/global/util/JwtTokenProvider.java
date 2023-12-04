package com.dgu.wantToGraduate.global.util;

import com.dgu.wantToGraduate.config.JwtConfig;
import com.dgu.wantToGraduate.domain.user.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final JwtConfig jwtConfig;
    private final UserRepository userRepository;

    public JwtTokenProvider(JwtConfig jwtConfig, UserRepository userRepository) {
        this.jwtConfig = jwtConfig;
        this.userRepository = userRepository;
    }

    private final Long ACCESS_TOKEN_EXPIRE_LENGTH = 1000L * 60 * 60 * 24 * 14; //2WEEK(개발을 위해 길게 잡음)
    private final Long REFRESH_TOKEN_EXPIRE_LENGTH = 1000L * 60 * 60 * 24 * 30; //30DAY

    public String createAccessToken(Long userId) {
        Date now = new Date(); //현재 시간
        Date validity = new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_LENGTH); //현재시간 + 2주
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, String.valueOf(jwtConfig.SECRET_KEY))
                .claim("userId", userId)
                .setIssuedAt(now) //token 발행 시간
                .setExpiration(validity)
                .compact();
    }

    public String createRefreshToken (Long userId){
        Date now = new Date(); //현재 시간
        Date validity = new Date(now.getTime() + REFRESH_TOKEN_EXPIRE_LENGTH); //현재시간 + 2주

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, String.valueOf(jwtConfig.SECRET_KEY))
                .claim("userId", userId)
                .setIssuedAt(now) //token 발행 시간
                .setExpiration(validity)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        return null;
    }
}
