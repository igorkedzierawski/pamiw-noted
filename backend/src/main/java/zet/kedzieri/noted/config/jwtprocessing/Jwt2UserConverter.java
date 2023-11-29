package zet.kedzieri.noted.config.jwtprocessing;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import zet.kedzieri.noted.user.NotedUserRepository;
import zet.kedzieri.noted.user.entity.NotedUser;

@Component
@RequiredArgsConstructor
public class Jwt2UserConverter implements Converter<Jwt, NotedUser> {

    private static final String PRINCIPAL_CLAIM_NAME = "preferred_username";
    private final NotedUserRepository notedUserRepo;

    @Override
    public NotedUser convert(Jwt jwt) {
        String principalClaimName = getPrincipalClaimName(jwt);
        return notedUserRepo.findUserByUsername(principalClaimName).orElse(null);
    }

    public static String getPrincipalClaimName(Jwt jwt) {
        return jwt.getClaim(PRINCIPAL_CLAIM_NAME);
    }

}
