package java16.restproject18.dto.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java16.restproject18.enums.RestType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class RestaurantGetAll {
    String name;
    String location;
    @Enumerated(EnumType.STRING)
    RestType restType;
    int numberOfEmployees;
    String service;
}
