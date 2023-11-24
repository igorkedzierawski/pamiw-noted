package zet.kedzieri.noted.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import zet.kedzieri.noted.user.auth.NoteUserOAuth2UserService;
import zet.kedzieri.noted.user.auth.NotedUserDetailsService;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final NotedUserDetailsService userDetailsService;
    private final NoteUserOAuth2UserService oAuth2MemberService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity sec) throws Exception {
        return sec
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/register").permitAll()
                        .requestMatchers("/api/v1/auth/logout").permitAll()
                        .requestMatchers("/api/v1/auth/login").permitAll()
                        .requestMatchers("/api/v1/auth/userinfo").authenticated()
                        .requestMatchers("/api/v1/note/**").authenticated()
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .anyRequest().permitAll()
                )
                .formLogin(login -> {
                    login.loginPage("/login");
                    login.loginProcessingUrl("/api/v1/auth/login");
                    login.defaultSuccessUrl("/");
                    login.failureUrl("/login?err=true");
                    login.permitAll();
                })
                .logout(lo -> {
                    lo.logoutUrl("/api/v1/auth/logout");
                    lo.deleteCookies("JSESSIONID");
                    lo.clearAuthentication(true);
                    lo.invalidateHttpSession(true);
                    lo.logoutSuccessUrl("/");
                })
                .oauth2Login(oauth2LoginConfig())
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .build();
    }

    @Bean
    public Customizer<OAuth2LoginConfigurer<HttpSecurity>> oauth2LoginConfig() {
        return oauth2 -> oauth2.userInfoEndpoint(userInfo -> {
            userInfo.oidcUserService(oAuth2MemberService::loadUser);
            userInfo.userService(oAuth2MemberService);
        });
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

}