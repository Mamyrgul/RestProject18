package java16.restproject18.dto.response;

import java16.restproject18.entites.MenuItem;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CheckResponse {
    String firstName;
    String lastName;
    List<MenuItem> name;
    double price;
}
