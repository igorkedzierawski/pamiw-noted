package zet.kedzieri.noted.user.entity.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import zet.kedzieri.noted.user.config.RegistrationConfig;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegistrationForm {

    @NotNull(message = "{registration_form.name.notnull}")
    @NotBlank(message = "{registration_form.name.notblank}")
    @Size(message = "{registration_form.name.size}",
            min = RegistrationConfig.NAME_MIN_LENGTH, max = RegistrationConfig.NAME_MAX_LENGTH)
    private String name;

    @NotNull(message = "{registration_form.surname.notnull}")
    @NotBlank(message = "{registration_form.surname.notblank}")
    @Size(message = "{registration_form.surname.size}",
            min = RegistrationConfig.SURNAME_MIN_LENGTH, max = RegistrationConfig.SURNAME_MAX_LENGTH)
    private String surname;

    @NotNull(message = "{registration_form.username.notnull}")
    @NotBlank(message = "{registration_form.username.notblank}")
    @Size(message = "{registration_form.username.size}",
            min = RegistrationConfig.USERNAME_MIN_LENGTH, max = RegistrationConfig.USERNAME_MAX_LENGTH)
    private String username;

    @NotNull(message = "{registration_form.password.notnull}")
    @NotBlank(message = "{registration_form.password.notblank}")
    @Size(message = "{registration_form.password.size}",
            min = RegistrationConfig.PASSWORD_MIN_LENGTH, max = RegistrationConfig.PASSWORD_MAX_LENGTH)
    private String password;

}