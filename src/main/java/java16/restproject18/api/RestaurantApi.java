package java16.restproject18.api;

import java16.restproject18.dto.request.RestaurantCreate;
import java16.restproject18.dto.request.RestaurantUpdate;
import java16.restproject18.dto.response.RestaurantEmployee;
import java16.restproject18.dto.response.RestaurantGetAll;
import java16.restproject18.dto.response.SimpleResponse;
import java16.restproject18.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantApi {
    private final RestaurantService restaurantService;

    @PostMapping
    public SimpleResponse saveRestaurant(@RequestBody RestaurantCreate restaurantRequest) {
        return restaurantService.addRestaurant(restaurantRequest);
    }
    @GetMapping
    public List<RestaurantGetAll> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }
    @PutMapping("/{id}")
    public SimpleResponse updateRestaurant(@PathVariable Long id, @RequestBody RestaurantUpdate restaurantUpdate) {
    return restaurantService.updateRestaurant(id, restaurantUpdate);
    }
    @DeleteMapping("/{id}")
    public SimpleResponse deleteRestaurant(@PathVariable Long id) {
        return restaurantService.deleteRestaurant(id);
    }
    @GetMapping("/byId/{id}")
    public RestaurantGetAll getRestaurant(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id);
    }
    @GetMapping("/withEmp/{id}")
    public RestaurantEmployee getRestaurantWithEmp(@PathVariable Long id) {
       return restaurantService.getRestaurantEmployeeCount(id);
    }
}
