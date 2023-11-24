package zet.kedzieri.noted.user.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import zet.kedzieri.noted.user.NotedUserRepository;

@Component
@RequiredArgsConstructor
public class NotedUserDetailsService implements UserDetailsService {

    private final NotedUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findNativeAuthUser(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: "+username));
    }

}
