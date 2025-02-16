package java16.restproject18.service;

import java16.restproject18.dto.request.CreateCategory;
import java16.restproject18.dto.response.SimpleResponse;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryService {
    SimpleResponse addCategory(Long menuId,CreateCategory category);
    SimpleResponse deleteCategory(Long id);
    SimpleResponse updateCategory(Long id, CreateCategory category);
    List<CreateCategory> getByMenuId(@Param("menuId") Long menuId);
    List<CreateCategory> getAllCategories();
    CreateCategory getCategoryById(@Param("categoryId") Long id);
}
