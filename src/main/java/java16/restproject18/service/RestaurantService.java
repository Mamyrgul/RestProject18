package java16.restproject18.service;

import java16.restproject18.dto.request.RestaurantCreate;
import java16.restproject18.dto.request.RestaurantUpdate;
import java16.restproject18.dto.response.RestaurantEmployee;
import java16.restproject18.dto.response.RestaurantGetAll;
import java16.restproject18.dto.response.SimpleResponse;
import java16.restproject18.entites.Restaurant;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantService {
 SimpleResponse addRestaurant(RestaurantCreate restaurantRequest);
  List<RestaurantGetAll> getAllRestaurants();
 SimpleResponse updateRestaurant(Long id, RestaurantUpdate restaurantUpdate);
 SimpleResponse deleteRestaurant(Long id);
 RestaurantGetAll getRestaurantById(@Param("restaurantId") Long id);
 RestaurantEmployee getRestaurantEmployeeCount(@Param("restaurantId") Long restaurantId);
}
