package java16.restproject18.repository;

import java16.restproject18.dto.response.UserForGetAll;
import java16.restproject18.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    @Query("""
    SELECT new java16.restproject18.dto.response.UserForGetAll(
        u.firstName, u.lastName, u.birthDate, u.role, u.experience
    ) 
    FROM User u 
    WHERE u.restaurant.id = :restaurantId
""")
    List<UserForGetAll> getAllByRestaurantId(@Param("restaurantId") Long restaurantId);
    @Query("""
select new java16.restproject18.dto.response.UserForGetAll(u.firstName, u.lastName, u.birthDate, u.role, u.experience) from User u
""")
    UserForGetAll findById(long id);

}
