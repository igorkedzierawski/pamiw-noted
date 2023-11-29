package zet.kedzieri.noted.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import zet.kedzieri.noted.config.jwtprocessing.Jwt2AuthenticationConverter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final Jwt2AuthenticationConverter authenticationConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity sec) throws Exception {
        return sec
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**").authenticated()
                        .requestMatchers("/api/v1/note/**").authenticated()
                        .anyRequest().permitAll()
                )
                .oauth2ResourceServer(o2rs -> o2rs
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(authenticationConverter)
                        )
                )
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .build();
    }

}