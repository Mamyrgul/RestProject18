package java16.restproject18.api;

import java16.restproject18.dto.request.CreateCategory;
import java16.restproject18.dto.response.SimpleResponse;
import java16.restproject18.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryApi {
    private final CategoryService categoryService;
    @GetMapping("/getAll")
    public List<CreateCategory> getAll() {
        return categoryService.getAllCategories();
    }
    @PostMapping("/create/{id}")
    public SimpleResponse create(@PathVariable Long id,@RequestBody CreateCategory category) {
        return categoryService.addCategory(id, category);
    }
    @PutMapping("/update/{id}")
    public SimpleResponse updateCategory(@PathVariable Long id, @RequestBody CreateCategory category) {
        return categoryService.updateCategory(id, category);
    }
    @GetMapping("/getById/{id}")
    public CreateCategory getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }
    @DeleteMapping("/delete/{id}")
    public SimpleResponse deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }
    @GetMapping("/getAllByMenuId/{id}")
    public List<CreateCategory> getAllByMenuId(@PathVariable Long id) {
       return categoryService.getByMenuId(id);
    }
}
