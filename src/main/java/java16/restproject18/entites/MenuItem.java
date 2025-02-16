package java16.restproject18.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "menuItems")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuItem {
    @Id
    @GeneratedValue(generator = "men_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "men_gen", sequenceName = "men_gen",allocationSize = 1)
    Long id;
    String name;
    String image;
    @PositiveOrZero
    BigDecimal price;
    String description;
    boolean isVegetarian;
    @OneToMany(mappedBy = "menuItem", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
    List<Category> category;
    @ManyToOne
    Restaurant restaurant;

    public MenuItem(String name, String image, BigDecimal price, String description, boolean isVegetarian) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.description = description;
        this.isVegetarian = isVegetarian;
    }
}
