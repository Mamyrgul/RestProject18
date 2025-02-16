package java16.restproject18.dto.request;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateMenu {
    String name;
    String image;
    @PositiveOrZero
    BigDecimal price;
    String description;
    boolean isVegetarian;
}
