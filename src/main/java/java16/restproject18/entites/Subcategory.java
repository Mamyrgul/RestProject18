package java16.restproject18.entites;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "subCategories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Subcategory {
    @Id
    @GeneratedValue(generator = "sub_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sub_gen", sequenceName = "sub_gen",allocationSize = 1)
    Long id;
    String name;
    @ManyToOne
    Category category;

    public Subcategory(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Subcategory{" +
               "name='" + name + '\'' +
               '}';
    }
}
