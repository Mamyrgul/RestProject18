package java16.restproject18.entites;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;

@Entity
@Table(name = "stopLists")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StopList {
    @Id
    @GeneratedValue(generator = "sto_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sto_gen", sequenceName = "sto_gen",allocationSize = 1)
    Long id;
    String reason;
    LocalDate stopDate;
    @OneToOne
    MenuItem menuItem;

    public StopList(String reason, LocalDate stopDate) {
        this.reason = reason;
        this.stopDate = stopDate;
    }
}
