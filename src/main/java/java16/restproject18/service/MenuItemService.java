package java16.restproject18.service;

import java16.restproject18.dto.request.CreateCategory;
import java16.restproject18.dto.request.CreateMenu;
import java16.restproject18.dto.response.SimpleResponse;
import java16.restproject18.entites.MenuItem;
import java16.restproject18.entites.User;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuItemService {
    SimpleResponse addMenuItem(String userRole, Long restaurantId, CreateMenu menu);
    SimpleResponse deleteMenuItem(Long id);
    SimpleResponse updateMenuItem(Long id, CreateMenu menu);
    CreateMenu getMenuItem(@Param("menuId") Long id);
    List<CreateMenu> getMenuItemsByRestaurantId(@Param("restaurantId") Long restaurantId);
    List<CreateMenu> getMenuItems();
    List<CreateCategory> searchByCategoryOrSubcategory(@Param("search") String search);
    List<MenuItem> sortByPrice(String order);
    List<CreateCategory> searchByCategoryAndVegetarian(@Param("search") String search,
                                                       @Param("isVegetarian") Boolean isVegetarian);

}
