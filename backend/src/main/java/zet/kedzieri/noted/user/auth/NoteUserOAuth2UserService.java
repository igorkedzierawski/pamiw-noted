package zet.kedzieri.noted.user.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import zet.kedzieri.noted.user.NotedUserRepository;
import zet.kedzieri.noted.user.NotedUserService;
import zet.kedzieri.noted.user.entity.NotedUser;

import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NoteUserOAuth2UserService extends DefaultOAuth2UserService {

    public static final String GITHUB_ID = "github";
    public static final String GOOGLE_ID = "google";

    private final NotedUserService notedUserService;
    private final NotedUserRepository notedUserRepo;

    @Override
    public NotedUser loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        return switch (registrationId) {
            case GITHUB_ID:
                yield loadUser_github(super.loadUser(userRequest));
            case GOOGLE_ID:
                yield loadUser_google(super.loadUser(userRequest));
            default:
                throw new OAuth2AuthenticationException(
                        new OAuth2Error(OAuth2ErrorCodes.INVALID_CLIENT),
                        "Registration with "+registrationId+" is unsupported"
                );
        };
    }

    private NotedUser loadUser_github(OAuth2User oAuth2User) {
        Map<String, Object> attrs = oAuth2User.getAttributes();
        String login = attrs.get("login").toString();
        Optional<NotedUser> foreignAuthUser = notedUserRepo.findForeignAuthUser(login, GITHUB_ID);
        if(foreignAuthUser.isPresent()) {
            return foreignAuthUser.get();
        }
        String name = login;
        String surname = "";
        return notedUserService.registerOAuthUser(name, surname, login, GITHUB_ID);
    }

    private NotedUser loadUser_google(OAuth2User oAuth2User) {
        Map<String, Object> attrs = oAuth2User.getAttributes();
        String email = attrs.get("email").toString();
        Optional<NotedUser> foreignAuthUser = notedUserRepo.findForeignAuthUser(email, GOOGLE_ID);
        if(foreignAuthUser.isPresent()) {
            return foreignAuthUser.get();
        }
        String name = attrs.get("name").toString();
        String surname = "";
        return notedUserService.registerOAuthUser(name, surname, email, GOOGLE_ID);
    }

}
