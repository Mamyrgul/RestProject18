package java16.restproject18.dto.request;
import jakarta.validation.constraints.*;
import java16.restproject18.enums.Role;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeApplication {

    @Email(message = "Email должен быть уникальным и валидным")
    @NotNull(message = "Email не может быть пустым")
    String email;

    @NotNull(message = "Имя не может быть пустым")
    String firstName;

    @NotNull(message = "Фамилия не может быть пустой")
     String lastName;

    @NotNull(message = "Дата рождения не может быть пустой")
    @Past(message = "Дата рождения должна быть в прошлом")
    LocalDate birthDate;

    @NotNull(message = "Телефон не может быть пустым")
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Неверный формат телефона")
    String phone;

    @NotNull(message = "Роль не может быть пустой\nРоль: официант или повар")
    Role role;
    @Min(value = 1, message = "Стаж работы должен быть не менее 1 года для официанта и 2 года для повара")
    @Max(value = 30, message = "Стаж работы должен быть не более 30 лет")
    int experience;
    @NotNull(message = "Пароль не может быть пустым")
    @Size(min = 4, message = "Пароль должен содержать хотя бы 4 символа")
    String password;
}

