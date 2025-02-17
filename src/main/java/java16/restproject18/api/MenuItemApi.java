package java16.restproject18.api;

import java16.restproject18.dto.request.CreateMenu;
import java16.restproject18.dto.response.SimpleResponse;
import java16.restproject18.entites.MenuItem;
import java16.restproject18.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menuItems")
@RequiredArgsConstructor
public class MenuItemApi {
    private final MenuItemService menuItemService;
    @GetMapping("/getAll")
    List<CreateMenu> getAll() {
       return menuItemService.getMenuItems();
    }
    @GetMapping("/getAllByResId/{id}")
    List<CreateMenu> getAllByResId(@PathVariable Long id) {
        return menuItemService.getMenuItemsByRestaurantId(id);
    }
    @GetMapping("/getById/{id}")
    CreateMenu getById(@PathVariable Long id) {
        return menuItemService.getMenuItem(id);
    }
    @PostMapping("/create/{role}/{resId}")
    SimpleResponse create(@PathVariable String role, @PathVariable Long resId, @RequestBody CreateMenu menu) {
        return menuItemService.addMenuItem(role,resId,menu);
    }
    @PutMapping("/update/{id}")
    SimpleResponse update(@PathVariable Long id, @RequestBody CreateMenu menu) {
        return menuItemService.updateMenuItem(id,menu);
    }
    @DeleteMapping("/delete/{id}")
    SimpleResponse delete(@PathVariable Long id) {
        return menuItemService.deleteMenuItem(id);
    }
    @GetMapping("/search/{search}")
    List<MenuItem> search(@PathVariable String search) {
        return menuItemService.searchByCategoryOrSubcategory(search);
    }
    @GetMapping("/searchByGegan/{search}/{isVegan}")
    List<MenuItem> searchByGegan(@PathVariable String search, @PathVariable boolean isVegan) {
     return menuItemService.searchByCategoryAndVegetarian(search, isVegan);
    }

}
