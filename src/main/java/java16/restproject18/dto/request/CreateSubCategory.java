package java16.restproject18.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateSubCategory {
    String name;

    public CreateSubCategory(String name) {
        this.name = name;
    }
}
