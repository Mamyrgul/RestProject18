package java16.restproject18.repository;

import java16.restproject18.dto.request.CreateSubCategory;
import java16.restproject18.entites.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepo extends JpaRepository<Subcategory,Long> {
    @Query("""
select new java16.restproject18.dto.request.CreateSubCategory(s.name) from Subcategory s where s.id= :id
""")
    CreateSubCategory getSubCategory(@Param("id") Long id);
    @Query("""
  select new java16.restproject18.dto.request.CreateSubCategory(s.name) from Subcategory s
where s.category.id = :categoryId order by s.name asc
""")
    List<CreateSubCategory> getSubCategories(@Param("categoryId") Long categoryId);
    @Query("""

            select new java16.restproject18.dto.request.CreateSubCategory(s.name) from Subcategory s
            group by s.category.id, s.name
""")
    List<CreateSubCategory> getAll();
}
