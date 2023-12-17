package com.dgu.wantToGraduate.config.test;

import com.dgu.wantToGraduate.domain.matching.entity.PreferBrand;
import com.dgu.wantToGraduate.domain.user.entity.User;
import com.dgu.wantToGraduate.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final UserRepository userRepository;
        public String redisString(){
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set("test", "test_value");
            String redis = (String) operations.get("test");
            log.info("[result]redis: {}", redis);
            return redis;
        }

    public PreferBrand testSaveOBJ(){
        Optional<User> user = userRepository.findById((Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        log.info("[result]user: {}", user.get());
        log.info("[result]user: {}", (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal());


        PreferBrand build = PreferBrand.builder()
                .user(user.get())
                .priority(1)
                .build();

        ValueOperations<String, Object> redisOP = redisTemplate.opsForValue();
        redisOP.set("test",build);

        Object result = redisOP.get("test");
        PreferBrand val = (PreferBrand) result;
        return val;

    }
}
