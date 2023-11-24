package zet.kedzieri.noted.note.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoteNotFoundByIdException extends RuntimeException {

    private final long id;

    public NoteNotFoundByIdException(long id) {
        super(String.format("Notatka o id %d nie istnieje", id));
        this.id = id;
    }
}
