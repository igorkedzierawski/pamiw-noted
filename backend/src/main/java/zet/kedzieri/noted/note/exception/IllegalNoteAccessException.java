package zet.kedzieri.noted.note.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.FORBIDDEN)
public class IllegalNoteAccessException extends RuntimeException {

    private final long id;

    public IllegalNoteAccessException(long id) {
        super(String.format("Notatka o id %d nie należy do Ciebie", id));
        this.id = id;
    }
}
