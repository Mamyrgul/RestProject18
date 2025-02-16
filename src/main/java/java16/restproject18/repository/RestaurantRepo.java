package java16.restproject18.repository;

import jakarta.transaction.Transactional;
import java16.restproject18.dto.request.RestaurantUpdate;
import java16.restproject18.dto.response.RestaurantEmployee;
import java16.restproject18.dto.response.RestaurantGetAll;
import java16.restproject18.dto.response.SimpleResponse;
import java16.restproject18.entites.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {
    @Query("""
select new java16.restproject18.dto.response.RestaurantGetAll(r.name,r.location,r.restType,r.numberOfEmployees,r.service) from Restaurant r
""")
    List<RestaurantGetAll> getAll();
    @Modifying
    @Query("UPDATE Restaurant r SET r.name = :#{#dto.name}, r.location = :#{#dto.location}, r.restType = :#{#dto.restType}, r.service = :#{#dto.service} WHERE r.id = :id")
    SimpleResponse updateRestaurant(@Param("id") Long id, @Param("dto") RestaurantUpdate dto);
    @Query("""
    SELECT new java16.restproject18.dto.response.RestaurantEmployee(
         r.name, r.numberOfEmployees,
        CASE
            WHEN r.numberOfEmployees >= 15 THEN 'Вакансия закрыта'
            ELSE 'Есть вакансия'
        END
    )
    FROM Restaurant r WHERE r.id = :restaurantId
""")
    RestaurantEmployee getRestaurantEmployeeCount(@Param("restaurantId") Long restaurantId);
    @Query("""
select new java16.restproject18.dto.response.RestaurantGetAll(r.name,r.location,r.restType,r.numberOfEmployees,r.service) from Restaurant r where r.id = :restaurantId
""")
    RestaurantGetAll getRestaurantById(@Param("restaurantId") Long id);
}
