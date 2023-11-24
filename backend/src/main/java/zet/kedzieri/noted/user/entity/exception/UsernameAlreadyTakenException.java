package zet.kedzieri.noted.user.entity.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.CONFLICT)
public class UsernameAlreadyTakenException extends RuntimeException {

    private final String username;

    public UsernameAlreadyTakenException(String username) {
        super(String.format("Nazwa użytkownika %s jest już zajęta", username));
        this.username = username;
    }
}
