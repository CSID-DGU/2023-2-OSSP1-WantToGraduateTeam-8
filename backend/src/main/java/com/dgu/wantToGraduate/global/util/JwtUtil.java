package com.dgu.wantToGraduate.global.util;

import com.dgu.wantToGraduate.config.JwtConfig;
import com.dgu.wantToGraduate.global.exception.CustomException;
import com.dgu.wantToGraduate.global.exception.ErrorCode;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtUtil {
    private final JwtConfig jwtConfig;

    public JwtUtil(final JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public Long getUserIdFromToken(String token) {
        log.info("token에서 ID 추출");
        Claims claims = Jwts.parser().setSigningKey(jwtConfig.SECRET_KEY).parseClaimsJws(token).getBody();//comment: 이 부분에서 오류가 발생하면, token이 유효하지 않은 것이다.
        return Long.parseLong(claims.get("userId").toString());
    }

    public Boolean validateToken(String token) {
        try {
            log.info("SECRET KEY :"+ jwtConfig.SECRET_KEY);

            Jwts.parser().setSigningKey(jwtConfig.SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            log.info("Sign 오류");
            e.printStackTrace();
            throw new CustomException(ErrorCode.INVALID_AUTH_TOKEN);
        } catch (IllegalArgumentException e) {
            log.info("잘못된 토큰");
            e.printStackTrace();
            throw new CustomException(ErrorCode.INVALID_AUTH_TOKEN);
        } catch (MalformedJwtException e) {
            log.info("토큰 잘림");
            e.printStackTrace();
            throw new CustomException(ErrorCode.INVALID_AUTH_TOKEN);
        } catch (ExpiredJwtException e) {
            log.info("만료된 토큰");
            e.printStackTrace();
            throw new CustomException(ErrorCode.INVALID_AUTH_TOKEN);
        } catch (NullPointerException e) {
            log.info("토큰 없음");
            e.printStackTrace();
            throw new CustomException(ErrorCode.INVALID_AUTH_TOKEN);
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 토큰");
            e.printStackTrace();
            throw new CustomException(ErrorCode.INVALID_AUTH_TOKEN);
        }
    }
}
