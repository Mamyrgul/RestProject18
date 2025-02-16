package java16.restproject18.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java16.restproject18.enums.RestType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RestaurantCreate {
    String name;
    String location;
    @Enumerated(EnumType.STRING)
    RestType restType;
    String service;
}
