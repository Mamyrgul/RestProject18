package java16.restproject18.entites;

import jakarta.persistence.*;
import java16.restproject18.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(generator = "use_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "use_gen", sequenceName = "use_gen",allocationSize = 1)
    Long id;
    String firstName;
    String lastName;
    LocalDate birthDate;
    @Column(unique = true)
    String email;
    String password;
    String phone;
    @Enumerated(EnumType.STRING)
    Role role;
    int experience;
    @ManyToOne
    Restaurant restaurant;
    @OneToMany(mappedBy = "user",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    List<Cheque> cheques;
    @PostPersist
    public void incrementEmployeeCount() {
        if (restaurant != null) {
            restaurant.setNumberOfEmployees(restaurant.getNumberOfEmployees() + 1);
        }
    }

    @PostRemove
    public void decrementEmployeeCount() {
        if (restaurant != null) {
            restaurant.setNumberOfEmployees(Math.max(0, restaurant.getNumberOfEmployees() - 1));
        }
    }

    @PrePersist
    private void setDefaultRole() {
        if (role == null) {
            this.role = Role.Admin;
        }
    }

    public User(String firstName, String lastName, LocalDate birthDate, String email, String password, String phone, Role role, int experience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.experience = experience;
    }
}
