package java16.restproject18.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Setter
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class RestaurantEmployee {
    String name;
    int numberOfEmployees;
    String vacancyStatus;
}
