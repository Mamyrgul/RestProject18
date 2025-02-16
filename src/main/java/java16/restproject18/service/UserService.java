package java16.restproject18.service;

import java16.restproject18.dto.request.EmployeeApplication;
import java16.restproject18.dto.request.UserSaveToAdmin;
import java16.restproject18.dto.response.SimpleResponse;
import java16.restproject18.dto.response.UserForGetAll;

import java.util.List;

public interface UserService {
    SimpleResponse createUser(UserSaveToAdmin userSaveToAdmin);
    boolean existsByEmail(String email);
    SimpleResponse deleteUser(Long userId);
    SimpleResponse submitApplication(EmployeeApplication application);
    SimpleResponse adminDecision(Long userId, Long restaurantId, boolean approve);
    List<UserForGetAll> getAllByRestaurantId(Long restaurantId);
    UserForGetAll findById(long id);
    SimpleResponse updateUser(Long userId, UserSaveToAdmin userSaveToAdmin);
    SimpleResponse updateEmployee(Long userId, EmployeeApplication employeeApplication);
}
