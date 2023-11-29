package zet.kedzieri.noted.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import zet.kedzieri.noted.Util;
import zet.kedzieri.noted.note.NoteRepository;
import zet.kedzieri.noted.user.entity.NotedUser;
import zet.kedzieri.noted.user.entity.exception.UsernameAlreadyTakenException;

@Component
@AllArgsConstructor
public class NotedUserService {

    private final NotedUserRepository repository;
    private final NoteRepository noteRepository;

    public NotedUser registerUser(String name, String surname, String username) {
        if (repository.findUserByUsername(username).isPresent()) {
            throw new UsernameAlreadyTakenException(username);
        }
        NotedUser save = repository.save(new NotedUser(name, surname, username));
        Util.injectRandomNotes(noteRepository, save);
        return save;
    }

}
