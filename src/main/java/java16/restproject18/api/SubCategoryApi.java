package java16.restproject18.api;

import java16.restproject18.dto.request.CreateSubCategory;
import java16.restproject18.dto.response.SimpleResponse;
import java16.restproject18.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subCategories")
@RequiredArgsConstructor
public class SubCategoryApi {
    private final SubCategoryService subCategoryService;
    @GetMapping("/getAll")
    public List<CreateSubCategory> getAll() {
        return subCategoryService.getAll();
    }
    @PostMapping("/create/{id}")
    public SimpleResponse create(@PathVariable Long id,@RequestBody CreateSubCategory createSubCategory) {
        return subCategoryService.createSubCategory(id, createSubCategory);
    }
    @PutMapping("/update/{id}")
    public SimpleResponse update(@PathVariable Long id,@RequestBody CreateSubCategory createSubCategory) {
        return subCategoryService.updateSubCategory(id, createSubCategory);
    }
    @DeleteMapping("/delete/{id}")
    public SimpleResponse delete(@PathVariable Long id) {
        return subCategoryService.deleteSubCategory(id);
    }
    @GetMapping("/getAllInCategory/{id}")
    public List<CreateSubCategory> getAllInCategory(@PathVariable Long id) {
     return subCategoryService.getSubCategories(id);
    }
    @GetMapping("/getById/{id}")
    public CreateSubCategory getById(@PathVariable Long id) {
        return subCategoryService.getSubCategory(id);
    }
}
