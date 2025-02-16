package java16.restproject18.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateCategory {
    String name;

    public CreateCategory(String name) {
        this.name = name;
    }
}
