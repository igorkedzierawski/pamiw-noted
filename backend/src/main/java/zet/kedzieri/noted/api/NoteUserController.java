package zet.kedzieri.noted.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import zet.kedzieri.noted.user.NotedUserService;
import zet.kedzieri.noted.user.entity.NotedUser;
import zet.kedzieri.noted.user.entity.dto.NotedUserDTO;
import zet.kedzieri.noted.user.entity.form.RegistrationForm;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class NoteUserController {

    private final NotedUserService notedUserService;

    @Operation(description = "Zarejestruj użytkownika. " +
            "Pole id jest ignorowane. " +
            "Może zostać wykonane przez zalogowanego użytkownika. ")
    @ResponseBody
    @PostMapping(value = "/register", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public NotedUserDTO register(@RequestBody @Validated RegistrationForm form) {
        return NotedUserDTO.from(notedUserService.registerNativeUser(form.getName(), form.getSurname(), form.getUsername(), form.getPassword()));
    }

    @Operation(description = "Zwraca informacje o zalogowanym użytkowniku.")
    @GetMapping("/userinfo")
    public NotedUserDTO userinfo(@AuthenticationPrincipal NotedUser ctx) {
        return ctx == null ? null : NotedUserDTO.from(ctx);
    }

}
