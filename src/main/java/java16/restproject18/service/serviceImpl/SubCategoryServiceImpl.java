package java16.restproject18.service.serviceImpl;

import jakarta.transaction.Transactional;
import java16.restproject18.dto.request.CreateSubCategory;
import java16.restproject18.dto.response.SimpleResponse;
import java16.restproject18.entites.Category;
import java16.restproject18.entites.Subcategory;
import java16.restproject18.repository.CategoryRepo;
import java16.restproject18.repository.SubCategoryRepo;
import java16.restproject18.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {
    private final SubCategoryRepo subCategoryRepo;
    private final CategoryRepo categoryRepo;

    @Override
    public SimpleResponse createSubCategory(Long categoryId, CreateSubCategory createSubCategory) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new RuntimeException("Категория не найден"));
        Subcategory subcategory = new Subcategory();
        subcategory.setName(createSubCategory.getName());
        subcategory.setCategory(category);
        subCategoryRepo.save(subcategory);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("successfully created subcategory")
                .build();
    }

    @Override
    public SimpleResponse deleteSubCategory(Long id) {
        subCategoryRepo.deleteById(id);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("successfully deleted subcategory")
                .build();
    }

    @Override
    public CreateSubCategory getSubCategory(Long id) {
        return subCategoryRepo.getSubCategory(id);
    }

    @Override
    public List<CreateSubCategory> getSubCategories(Long categoryId) {
        return subCategoryRepo.getSubCategories(categoryId);
    }

    @Override
    public List<CreateSubCategory> getAll() {
        return subCategoryRepo.getAll();
    }

    @Override
    public SimpleResponse updateSubCategory(Long id, CreateSubCategory createSubCategory) {
        Subcategory subcategory = subCategoryRepo.findById(id).orElseThrow(()-> new RuntimeException("Сабкатегория не найдено"));
        subcategory.setName(createSubCategory.getName());
        subCategoryRepo.save(subcategory);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("successfully updated subcategory")
                .build();
    }
}
