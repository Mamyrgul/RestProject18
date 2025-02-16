package java16.restproject18.api;

import java16.restproject18.dto.request.EmployeeApplication;
import java16.restproject18.dto.request.UserSaveToAdmin;
import java16.restproject18.dto.response.SimpleResponse;
import java16.restproject18.dto.response.UserForGetAll;
import java16.restproject18.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserSerApi {
    private final UserService userService;

    @PostMapping("/create")
    public SimpleResponse createUser(@RequestBody UserSaveToAdmin userSaveToAdmin) {
        return userService.createUser(userSaveToAdmin);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @PostMapping("/entering")
    public SimpleResponse enterUser(@RequestBody EmployeeApplication employeeApplication) {
        return userService.submitApplication(employeeApplication);
    }

    @PostMapping("/check/{userId}/{restaurantId}/{approve}")
    public SimpleResponse checkByAdmin(@PathVariable Long userId,
                                       @PathVariable Long restaurantId,
                                       @PathVariable boolean approve) {
        return userService.adminDecision(userId, restaurantId, approve);
    }
    @GetMapping("/getAll/{restaurantId}")
    public List<UserForGetAll> getAll(@PathVariable Long restaurantId) {
       return userService.getAllByRestaurantId(restaurantId);
    }
    @GetMapping("/findById/{id}")
    public UserForGetAll findById(@PathVariable Long id) {
        return userService.findById(id);
    }
    @PostMapping("/updateByAdmin/{id}")
    public SimpleResponse updateByAdmin(@PathVariable Long id,@RequestBody UserSaveToAdmin userSaveToAdmin) {
      return userService.updateUser(id, userSaveToAdmin);
    }
    @PostMapping("/updateRes/{id}")
    public SimpleResponse updateRes(@PathVariable Long id,@RequestBody EmployeeApplication employeeApplication) {
        return userService.updateEmployee(id, employeeApplication);
    }
}