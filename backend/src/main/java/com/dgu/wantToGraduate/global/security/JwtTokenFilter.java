package com.dgu.wantToGraduate.global.security;

import com.dgu.wantToGraduate.global.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    public JwtTokenFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("JwtTokenFilter 진입");

        String authorizationHeader = request.getHeader("Authorization");
        log.info("authorizationHeader: " + authorizationHeader);

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwtToken = authorizationHeader.substring(7);
            log.info("jwtToken: " + jwtToken);

            try{
                jwtUtil.validateToken(jwtToken);
                log.info("(토큰 검증 성공)유효한 토큰입니다.");

                Long userId = jwtUtil.getUserIdFromToken(jwtToken);
                log.info("(해당 토큰의 유저 아이디)userId: " + userId);
                UsernamePasswordAuthenticationToken authenticationToken
                        = new UsernamePasswordAuthenticationToken(userId, null, null);
                log.info("(인증 객체 생성 성공)authenticationToken: " + authenticationToken);

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                log.info("(SecurityContextHolder에 인증 객체 저장 성공)SecurityContextHolder: " + SecurityContextHolder.getContext());

                filterChain.doFilter(request, response);
            } catch (Exception e) {
                log.info("(토큰 검증 실패)유효하지 않은 토큰입니다.");
                SecurityContextHolder.clearContext();
                filterChain.doFilter(request, response);
            }
        } else{
            log.info("토큰이 없습니다.");
            filterChain.doFilter(request, response);
        }
    }
}
