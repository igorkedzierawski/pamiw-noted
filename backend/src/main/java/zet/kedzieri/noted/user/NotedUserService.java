package zet.kedzieri.noted.user;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import zet.kedzieri.noted.Util;
import zet.kedzieri.noted.note.NoteRepository;
import zet.kedzieri.noted.user.entity.NotedUser;
import zet.kedzieri.noted.user.entity.exception.UsernameAlreadyTakenException;

@Component
@AllArgsConstructor
public class NotedUserService {

    private final NotedUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final NoteRepository noteRepository;

    public NotedUser registerNativeUser(String name, String surname, String username, String password) {
        if (repository.findNativeAuthUser(username).isPresent()) {
            throw new UsernameAlreadyTakenException(username);
        }
        NotedUser save = repository.save(new NotedUser(name, surname, username, passwordEncoder.encode(password), null));
        Util.injectRandomNotes(noteRepository, save);
        return save;
    }

    public NotedUser registerOAuthUser(String name, String surname, String username, String foreignAuth) {
        if (repository.findForeignAuthUser(username, foreignAuth).isPresent()) {
            throw new UsernameAlreadyTakenException(username);
        }
        NotedUser save = repository.save(new NotedUser(name, surname, username, null, foreignAuth));
        Util.injectRandomNotes(noteRepository, save);
        return save;
    }

}
