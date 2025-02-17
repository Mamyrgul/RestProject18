package java16.restproject18.entites;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category {
    @Id
    @GeneratedValue(generator = "cat_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "cat_gen", sequenceName = "cat_gen",allocationSize = 1)
    Long id;
    String name;
    @OneToMany(mappedBy = "category",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE},fetch = FetchType.EAGER)
    List<Subcategory> subcategories;
    @ManyToOne
    MenuItem menuItem;

    public Category(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
               "name='" + name + '\'' +
               '}';
    }
}
