package java16.restproject18.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import java16.restproject18.entites.Restaurant;
import java16.restproject18.enums.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserSaveToAdmin {
    String firstName;
    String lastName;
    LocalDate birthDate;
    @Column(unique = true)
    String email;
    String password;
    String phone;
    @Enumerated(EnumType.STRING)
    Role role;
    int experience;
    Long restaurantId;

}
