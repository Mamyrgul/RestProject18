package java16.restproject18.dto.response;

import java16.restproject18.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class UserForGetAll {
    String firstName;
    String lastName;
    LocalDate birthDate;
    Role role;
    int experience;
}
