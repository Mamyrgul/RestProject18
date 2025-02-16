package java16.restproject18.entites;

import jakarta.persistence.*;
import java16.restproject18.enums.RestType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "restaurants")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Restaurant {
    @Id
    @GeneratedValue(generator = "res_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "res_gen", sequenceName = "res_gen",allocationSize = 1)
    Long id;
    String name;
    String location;
    @Enumerated(EnumType.STRING)
    RestType restType;
    int numberOfEmployees;
    String service;
    @OneToMany(mappedBy = "restaurant",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
    List<User> users;
    @OneToMany(mappedBy = "restaurant",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
    List<MenuItem> menuItems;

    public Restaurant(String name, String location, RestType restType, int numberOfEmployees, String service) {
        this.name = name;
        this.location = location;
        this.restType = restType;
        this.numberOfEmployees = numberOfEmployees;
        this.service = service;
    }
}
