package java16.restproject18.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter

@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WaiterCheck {
    double totalCheck;
    LocalDate checkDate;

    public WaiterCheck(double totalCheck, LocalDate checkDate) {
        this.totalCheck = totalCheck;
        this.checkDate = checkDate;
    }
}
