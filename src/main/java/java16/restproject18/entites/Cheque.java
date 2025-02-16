package java16.restproject18.entites;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "chequies")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cheque {
    @Id
    @GeneratedValue(generator = "che_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "che_gen", sequenceName = "che_gen",allocationSize = 1)
    Long id;
    BigDecimal priceAverage;
    LocalDate creationDate;
    @ManyToOne
    User user;
    @ManyToMany
    List<MenuItem> menuItems;

    public Cheque(BigDecimal priceAverage, LocalDate creationDate) {
        this.priceAverage = priceAverage;
        this.creationDate = creationDate;
    }
    @PrePersist
    public void prePersist() {
        creationDate = LocalDate.now();
    }
}
