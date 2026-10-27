package com.result.view.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{


//        http.authorizeHttpRequests(httpRequests->
//                httpRequests.requestMatchers("/admin/add-result").authenticated()
//                        .requestMatchers("/admin/add-result-action").authenticated()
//                        .anyRequest().permitAll())
//                .formLogin(Customizer.withDefaults());


        http.authorizeHttpRequests(httpRequests ->
                        httpRequests
                                .requestMatchers("/admin/add-result").authenticated()
                                .requestMatchers("/admin/add-result-action").authenticated()
                                .anyRequest().permitAll()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/user-login")
                                .loginProcessingUrl("/do-login")
                                .successForwardUrl("/admin/result-page")
//                                .failureForwardUrl("/user-login")
                                .permitAll()
                ).logout(logout->logout.logoutUrl("/user-logout")
                        .logoutSuccessUrl("/user-login?logout")
                        .permitAll());





        DefaultSecurityFilterChain build = http.build();
        return build;
    }
}
