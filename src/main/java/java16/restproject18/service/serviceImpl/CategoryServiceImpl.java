package java16.restproject18.service.serviceImpl;

import jakarta.transaction.Transactional;
import java16.restproject18.dto.request.CreateCategory;
import java16.restproject18.dto.response.SimpleResponse;
import java16.restproject18.entites.Category;
import java16.restproject18.entites.MenuItem;
import java16.restproject18.repository.CategoryRepo;
import java16.restproject18.repository.MenuItemRepo;
import java16.restproject18.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final MenuItemRepo menuItemRepo;
    @Override
    @Transactional
    public SimpleResponse addCategory(Long menuId, CreateCategory category) {
        MenuItem menuItem = menuItemRepo.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Not found menu item"));

        Category category1 = new Category();
        category1.setName(category.getName());
        category1.setMenuItem(menuItem);

        categoryRepo.save(category1);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.CREATED)
                .message("Successfully added category")
                .build();
    }


    @Override
    public SimpleResponse deleteCategory(Long id) {
        categoryRepo.deleteById(id);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("successfully deleted category")
                .build();
    }

    @Override
    public SimpleResponse updateCategory(Long id, CreateCategory category) {
        Category category1= categoryRepo.findById(id).orElseThrow(() -> new RuntimeException("Категория не найден"));
        category1.setName(category.getName());
        categoryRepo.save(category1);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("successfully updated category")
                .build();
    }

    @Override
    public List<CreateCategory> getByMenuId(Long menuId) {
        return categoryRepo.getByMenuId(menuId);
    }

    @Override
    public List<CreateCategory> getAllCategories() {
        return categoryRepo.getAllCategories();
    }

    @Override
    public CreateCategory getCategoryById(Long id) {
        return categoryRepo.getCategoryById(id);
    }
}
