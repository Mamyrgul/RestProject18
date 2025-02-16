package java16.restproject18.service.serviceImpl;

import jakarta.transaction.Transactional;
import java16.restproject18.dto.request.RestaurantCreate;
import java16.restproject18.dto.request.RestaurantUpdate;
import java16.restproject18.dto.response.RestaurantEmployee;
import java16.restproject18.dto.response.RestaurantGetAll;
import java16.restproject18.dto.response.SimpleResponse;
import java16.restproject18.entites.Restaurant;
import java16.restproject18.repository.RestaurantRepo;
import java16.restproject18.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepo restaurantRepo;
    @Override
    public SimpleResponse addRestaurant(RestaurantCreate restaurantRequest) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantRequest.getName());
        restaurant.setLocation(restaurantRequest.getLocation());
        restaurant.setRestType(restaurantRequest.getRestType());
        restaurant.setService(restaurantRequest.getService());
        restaurantRepo.save(restaurant);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Restaurant added")
                .build();
    }

    @Override
    public List<RestaurantGetAll> getAllRestaurants() {
        return restaurantRepo.getAll();
    }
    public SimpleResponse updateRestaurant(Long id, RestaurantUpdate restaurantUpdate) {
        Restaurant existingRestaurant = restaurantRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ресторан не найден"));

        if (restaurantUpdate.getName() != null) {
            existingRestaurant.setName(restaurantUpdate.getName());
        }
        if (restaurantUpdate.getLocation() != null) {
            existingRestaurant.setLocation(restaurantUpdate.getLocation());
        }
        if (restaurantUpdate.getRestType() != null) {
            existingRestaurant.setRestType(restaurantUpdate.getRestType());
        }
        if (restaurantUpdate.getService() != null) {
            existingRestaurant.setService(restaurantUpdate.getService());
        }
        restaurantRepo.save(existingRestaurant);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Restaurant updated")
                .build();
    }

    @Override
    public SimpleResponse deleteRestaurant(Long id) {
        restaurantRepo.deleteById(id);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Restaurant deleted")
                .build();
    }

    @Override
    public RestaurantGetAll getRestaurantById(Long id) {
        return restaurantRepo.getRestaurantById(id);
    }


    @Override
    public RestaurantEmployee getRestaurantEmployeeCount(Long restaurantId) {
        return restaurantRepo.getRestaurantEmployeeCount(restaurantId);
    }
}
