package java16.restproject18.service.serviceImpl;

import jakarta.transaction.Transactional;
import java16.restproject18.dto.request.CreateCategory;
import java16.restproject18.dto.request.CreateMenu;
import java16.restproject18.dto.response.SimpleResponse;
import java16.restproject18.entites.MenuItem;
import java16.restproject18.enums.Role;
import java16.restproject18.repository.MenuItemRepo;
import java16.restproject18.repository.RestaurantRepo;
import java16.restproject18.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {
    private final MenuItemRepo menuItemRepo;
    private final RestaurantRepo restaurantRepo;

    @Override
    public SimpleResponse addMenuItem(String role, Long restaurantId, CreateMenu menu) {
        if (!role.equals(Role.Chef.name()) && !role.equals(Role.Admin.name())) {
          throw new RuntimeException("Только админ и повар может создать меню(Chef,Admin)");
        }
        restaurantRepo.findById(restaurantId).orElseThrow(() -> new RuntimeException("not found with this id"));

        MenuItem menuItem = new MenuItem();
        menuItem.setName(menu.getName());
        menuItem.setImage(menu.getImage());
        menuItem.setPrice(menu.getPrice());
        menuItem.setDescription(menu.getDescription());
        menuItem.setVegetarian(menu.isVegetarian());
        menuItemRepo.save(menuItem);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.CREATED)
                .message("Successfully added menu item")
                .build();
    }

    @Override
    public SimpleResponse deleteMenuItem(Long id) {
        menuItemRepo.deleteById(id);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.ACCEPTED)
                .message("successfully removed menu item")
                .build();
    }

    @Override
    public SimpleResponse updateMenuItem(Long id, CreateMenu menu) {
       MenuItem menuItem= menuItemRepo.findById(id).orElseThrow(() -> new RuntimeException("not found with this id"));

        menuItem.setName(menu.getName());
        menuItem.setImage(menu.getImage());
        menuItem.setPrice(menu.getPrice());
        menuItem.setDescription(menu.getDescription());
        menuItem.setVegetarian(menu.isVegetarian());
        menuItemRepo.save(menuItem);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.CREATED)
                .message("Successfully updated menu item")
                .build();
    }

    @Override
    public CreateMenu getMenuItem(Long id) {
        return menuItemRepo.getMenuItem(id);
    }

    @Override
    public List<CreateMenu> getMenuItemsByRestaurantId(Long restaurantId) {
        return menuItemRepo.getMenuItemsByRestaurantId(restaurantId);
    }

    @Override
    public List<CreateMenu> getMenuItems() {
        return menuItemRepo.getMenuItems();
    }

    @Override
    public List<CreateCategory> searchByCategoryOrSubcategory(String search) {
        return menuItemRepo.searchByCategoryOrSubcategory(search);
    }


    @Override
    public List<MenuItem> sortByPrice(String order) {
        Sort sort = Sort.by("price");

        if ("desc".equalsIgnoreCase(order)) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }
        return menuItemRepo.findAll(sort);
    }

    @Override
    public List<CreateCategory> searchByCategoryAndVegetarian(String search, Boolean isVegetarian) {
        return menuItemRepo.searchByCategoryAndVegetarian(search, isVegetarian);
    }


}
