package com.dgu.wantToGraduate.config;

//import com.dgu.wantToGraduate.config.auth.PrincipalDetailsService;
//import com.dgu.wantToGraduate.config.handler.LoginFailureHandler;
//import com.dgu.wantToGraduate.config.handler.LoginSuccessHandler;
import com.dgu.wantToGraduate.global.security.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity(debug = true)
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenFilter jwtTokenFilter;
//    @Autowired
//    private PrincipalDetailsService PrincipalDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.csrf().disable()
                .cors()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .antMatchers("/user/login/**", "/user/join/**","/","/error/**").permitAll()
                .antMatchers("/matching/run/**").permitAll() //TODO⚠️: 매칭 스트레스 테스트를 위해 임시로 허용
                .anyRequest().authenticated()
                .and()

                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

//                //✅TODO: form session login으로 변경
//                .formLogin()
//                .usernameParameter("email")
//                .passwordParameter("password")
//                .loginProcessingUrl("/user/login")
//                .successHandler(new LoginSuccessHandler()) //200
//                .failureHandler(new LoginFailureHandler());//401

    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(PrincipalDetailsService);
//    }
}
