package zet.kedzieri.noted.config.jwtprocessing;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import zet.kedzieri.noted.user.NotedUserService;
import zet.kedzieri.noted.user.entity.NotedUser;

import static zet.kedzieri.noted.config.jwtprocessing.Jwt2UserConverter.getPrincipalClaimName;

@Component
@RequiredArgsConstructor
public class Jwt2AuthenticationConverter implements Converter<Jwt, JwtAuthenticationToken> {

    private final Jwt2AuthoritiesConverter authoritiesConverter;
    private final Jwt2UserConverter userConverter;
    private final NotedUserService notedUserService;

    @Override
    public JwtAuthenticationToken convert(Jwt jwt) {
        NotedUser user = userConverter.convert(jwt);
        if(user == null) {
            String username = getPrincipalClaimName(jwt);
            user = notedUserService.registerUser("SOME_NAME", "SOME_SURNAME", username);
        }
        return new JwtAuthenticationToken(jwt, authoritiesConverter.convert(jwt), user.getUsername());
    }

}
