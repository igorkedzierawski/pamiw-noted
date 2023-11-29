package zet.kedzieri.noted.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import zet.kedzieri.noted.config.jwtprocessing.JwtAuthed;
import zet.kedzieri.noted.user.entity.NotedUser;
import zet.kedzieri.noted.user.entity.dto.NotedUserDTO;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    @Operation(description = "Zwraca informacje o zalogowanym użytkowniku.")
    @GetMapping("/userinfo")
    public NotedUserDTO userinfo(@JwtAuthed NotedUser ctx) {
        return NotedUserDTO.from(ctx);
    }

}
