package java16.restproject18.service;

import java16.restproject18.dto.request.CreateSubCategory;
import java16.restproject18.dto.response.SimpleResponse;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubCategoryService {
    SimpleResponse createSubCategory(Long categoryId, CreateSubCategory createSubCategory);
    SimpleResponse deleteSubCategory(Long id);
    CreateSubCategory getSubCategory(@Param("id") Long id);
    List<CreateSubCategory> getSubCategories(@Param("categoryId") Long categoryId);
    List<CreateSubCategory> getAll();
    SimpleResponse updateSubCategory(@Param("id") Long id, CreateSubCategory createSubCategory);
}
