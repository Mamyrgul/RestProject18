package java16.restproject18.repository;

import java16.restproject18.dto.request.CreateCategory;
import java16.restproject18.dto.request.CreateMenu;
import java16.restproject18.entites.Category;
import java16.restproject18.entites.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MenuItemRepo extends JpaRepository<MenuItem, Long> {
    @Query("""
select new java16.restproject18.dto.request.CreateMenu(m.name,m.image,m.price,m.description,m.isVegetarian) from MenuItem m where m.id= :menuId
""")
    CreateMenu getMenuItem(@Param("menuId") Long id);
    @Query("""
select new java16.restproject18.dto.request.CreateMenu(m.name,m.image,m.price,m.description,m.isVegetarian) from MenuItem m where m.restaurant=:restaurantId
""")
    List<CreateMenu> getMenuItemsByRestaurantId(@Param("restaurantId") Long restaurantId);
    @Query("""
select new java16.restproject18.dto.request.CreateMenu(m.name,m.image,m.price,m.description,m.isVegetarian) from MenuItem m
""")
    List<CreateMenu> getMenuItems();
    @Query("""
    SELECT DISTINCT c
    FROM MenuItem m
    JOIN m.category c
    LEFT JOIN c.subcategories s
    WHERE (COALESCE(:search, '') = '' OR 
           LOWER(c.name) LIKE LOWER(CONCAT('%', :search, '%')) OR 
           LOWER(s.name) LIKE LOWER(CONCAT('%', :search, '%')))
""")
    List<Category> searchByCategoryOrSubcategory(@Param("search") String search);


    @Query("""
    SELECT DISTINCT c
    FROM MenuItem m
    JOIN m.category c
    LEFT JOIN c.subcategories s
    WHERE (:search IS NULL OR
           LOWER(c.name) LIKE LOWER(CONCAT('%', :search, '%')) OR
           LOWER(s.name) LIKE LOWER(CONCAT('%', :search, '%')))
      AND (:isVegetarian IS NULL OR m.isVegetarian = :isVegetarian)
""")
    List<Category> searchByCategoryAndVegetarian(@Param("search") String search,
                                                 @Param("isVegetarian") Boolean isVegetarian);

}
