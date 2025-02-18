package java16.restproject18.repository;

import java16.restproject18.dto.request.CreateCategory;
import java16.restproject18.entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    @Query("""
select new java16.restproject18.dto.request.CreateCategory(c.name) from Category c
""")
    List<CreateCategory> getAllCategories();
@Query("""
select new  java16.restproject18.dto.request.CreateCategory(c.name) from Category c where c.id= :categoryId
""")
    CreateCategory getCategoryById(@Param("categoryId") Long id);
@Query("""
select new java16.restproject18.dto.request.CreateCategory(c.name) from Category c where c.menuItem.id= :menuItemId
""")
    List<CreateCategory> getByMenuId(@Param("menuId") Long menuId);
}
