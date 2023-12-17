package com.dgu.wantToGraduate.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Value("osspcluster.vfxvji.ng.0001.apn2.cache.amazonaws.com")
    private String host;

    @Value("6379")
    private int port;

    /**
     https://redis.com/blog/jedis-vs-lettuce-an-exploration/
     Lettuce: 멀티 쓰레드 환경에서 Thread-Safe한 Redis 클라이언트로 비동기 처리 방식 netty에 의해 관리된다.
     비동기 방식으로 요청하기에 TPS/CPU/Connection 개수와 응답속도 등 전 분야에서 Jedis 보다 뛰어나다.
     Jedis  : 멀티 쓰레드 환경에서 Thread-unsafe 하며 Connection pool을 이용해 멀티쓰레드 환경을 구성한다.
     Jedis 인스턴스와 연결할 때마다 Connection pool을 불러오고 스레드 갯수가 늘어난다면 시간이 상당히 소요될 수 있다.

     Lettuce vs Jedis 성능 비교 : https://jojoldu.tistory.com/418
     host, port를 명시적으로 주입하는 이유는 Test 코드 실행시 컨테이너 port가 매핑되기 위함이다.
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    /**
     * RedisTemplate: Redis data access code를 간소화 하기 위해 제공되는 클래스이다.
     * 주어진 객체들을 자동으로 직렬화/역직렬화 하며 binary 데이터를 Redis에 저장한다.
     * 기본설정은 JdkSerializationRedisSerializer 이다.
     * <p>
     * Serializer 종류
     * - JdkSerializationRedisSerializer: 디폴트로 등록되어있는 Serializer이다.
     * - StringRedisSerializer: String 값을 정상적으로 읽어서 저장한다. 그러나 엔티티나 VO같은 타입은 cast 할 수 없다.
     * - Jackson2JsonRedisSerializer(classType.class): classType 값을 json 형태로 저장한다. 특정 클래스(classType)에게만 직속되어있다는 단점이 있다.
     * - GenericJackson2JsonRedisSerializer: 모든 classType을 json 형태로 저장할 수 있는 범용적인 Jackson2JsonRedisSerializer이다.
     * 캐싱에 클래스 타입도 저장된다는 단점이 있지만 RedisTemplate을 이용해 다양한 타입 객체를 캐싱할 때 사용하기에 좋다
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer(); // 모든 클래스 타입을 json 형태로 저장할 수 있는 범용적인 Jackson2JsonRedisSerializer이다.
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>(); // RedisTemplate 객체를 생성한다.
        redisTemplate.setConnectionFactory(redisConnectionFactory); // RedisConnectionFactory를 주입한다.
        redisTemplate.setKeySerializer(new StringRedisSerializer()); // key는 String 타입으로 저장한다.
        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer); // value는 genericJackson2JsonRedisSerializer로 저장한다.
        return redisTemplate;
    }
}
