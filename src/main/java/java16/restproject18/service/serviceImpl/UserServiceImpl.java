package java16.restproject18.service.serviceImpl;

import jakarta.transaction.Transactional;
import java16.restproject18.dto.request.EmployeeApplication;
import java16.restproject18.dto.request.UserSaveToAdmin;
import java16.restproject18.dto.response.SimpleResponse;
import java16.restproject18.dto.response.UserForGetAll;
import java16.restproject18.entites.Restaurant;
import java16.restproject18.entites.User;
import java16.restproject18.enums.Role;
import java16.restproject18.repository.RestaurantRepo;
import java16.restproject18.repository.UserRepo;
import java16.restproject18.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final RestaurantRepo restaurantRepo;

    public SimpleResponse createUser(UserSaveToAdmin userSaveToAdmin) {
        if (userRepo.existsByEmail(userSaveToAdmin.getEmail())) {
            return new SimpleResponse(HttpStatus.BAD_REQUEST, "Такой email уже существует!");
        }
        Restaurant restaurant = restaurantRepo.findById(userSaveToAdmin.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Ресторан не найден"));

        User user = new User();
        user.setFirstName(userSaveToAdmin.getFirstName());
        user.setLastName(userSaveToAdmin.getLastName());
        user.setBirthDate(userSaveToAdmin.getBirthDate());
        user.setEmail(userSaveToAdmin.getEmail());
        user.setPassword(userSaveToAdmin.getPassword());
        user.setPhone(userSaveToAdmin.getPhone());
        user.setRole(userSaveToAdmin.getRole());
        user.setExperience(userSaveToAdmin.getExperience());
        user.setRestaurant(restaurant);

        userRepo.save(user);
        restaurant.setNumberOfEmployees(restaurant.getNumberOfEmployees() + 1);
        restaurantRepo.save(restaurant);

        return new SimpleResponse(HttpStatus.OK, "Сотрудник успешно создан!");
    }

    @Override
    public boolean existsByEmail(String email) {
        if (email == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public SimpleResponse deleteUser(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        Restaurant restaurant = user.getRestaurant();

        if (restaurant != null) {
            restaurant.setNumberOfEmployees(restaurant.getNumberOfEmployees() - 1);
            restaurantRepo.save(restaurant);
        }

        userRepo.deleteById(userId);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Сотрудник успешно удален")
                .build();
    }

    public SimpleResponse submitApplication(EmployeeApplication application) {
        if (userRepo.existsByEmail(application.getEmail())) {
            return new SimpleResponse(HttpStatus.BAD_REQUEST, "Email уже существует");
        }

        LocalDate currentDate = LocalDate.now();
        int age = Period.between(application.getBirthDate(), currentDate).getYears();

        if (application.getRole() == Role.Chef && (age < 25 || age > 45)) {
            return new SimpleResponse(HttpStatus.BAD_REQUEST, "Возраст повара должен быть от 25 до 45 лет");
        }
        if (application.getRole() == Role.Waiter && (age < 18 || age > 30)) {
            return new SimpleResponse(HttpStatus.BAD_REQUEST, "Возраст официанта должен быть от 18 до 30 лет");
        }

        if (application.getRole() == Role.Chef && application.getExperience() < 2) {
            return new SimpleResponse(HttpStatus.BAD_REQUEST, "Стаж работы повара должен быть не менее 2 лет");
        }
        if (application.getRole() == Role.Waiter && application.getExperience() < 1) {
            return new SimpleResponse(HttpStatus.BAD_REQUEST, "Стаж работы официанта должен быть не менее 1 года");
        }

        User newUser = new User();
        newUser.setFirstName(application.getFirstName());
        newUser.setLastName(application.getLastName());
        newUser.setEmail(application.getEmail());
        newUser.setBirthDate(application.getBirthDate());
        newUser.setPhone(application.getPhone());
        newUser.setRole(application.getRole());
        newUser.setExperience(application.getExperience());

        newUser.setRestaurant(null);

        userRepo.save(newUser);
        return new SimpleResponse(HttpStatus.OK, "Заявка успешно отправлена");
    }

    public SimpleResponse adminDecision(Long userId, Long restaurantId, boolean approve) {
        Optional<User> optionalUser = userRepo.findById(userId);


        if (optionalUser.isEmpty()) {
            return new SimpleResponse(HttpStatus.NOT_FOUND, "Пользователь не найден");
        }
        User user = optionalUser.get();

        if (approve) {
            Restaurant restaurant = restaurantRepo.findById(restaurantId).orElse(null);

            if (restaurant == null) {
                return new SimpleResponse(HttpStatus.BAD_REQUEST, "Ресторан не найден");
            }

            user.setRestaurant(restaurant);
            userRepo.save(user);
            return new SimpleResponse(HttpStatus.OK, "Заявка принята и назначен ресторан");
        } else {
            userRepo.delete(user);
            return new SimpleResponse(HttpStatus.OK, "Заявка отклонена, пользователь удален");
        }
    }

    @Override
    public List<UserForGetAll> getAllByRestaurantId(Long restaurantId) {
        return userRepo.getAllByRestaurantId(restaurantId);
    }

    @Override
    public UserForGetAll findById(long id) {
        return userRepo.findById(id);
    }

    @Override
    public SimpleResponse updateUser(Long userId, UserSaveToAdmin userSaveToAdmin) {
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message("With this id is not found")
                    .build();
        }
        user.setFirstName(userSaveToAdmin.getFirstName());
        user.setLastName(userSaveToAdmin.getLastName());
        user.setEmail(userSaveToAdmin.getEmail());
        user.setBirthDate(userSaveToAdmin.getBirthDate());
        user.setPassword(userSaveToAdmin.getPassword());
        user.setPhone(userSaveToAdmin.getPhone());
        user.setRole(userSaveToAdmin.getRole());
        user.setExperience(userSaveToAdmin.getExperience());
        userRepo.save(user);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully updated user")
                .build();
    }

    @Override
    public SimpleResponse updateEmployee(Long userId, EmployeeApplication employeeApplication) {
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message("With this id is not found")
                    .build();
        }
        user.setFirstName(employeeApplication.getFirstName());
        user.setLastName(employeeApplication.getLastName());
        user.setEmail(employeeApplication.getEmail());
        user.setBirthDate(employeeApplication.getBirthDate());
        user.setPhone(employeeApplication.getPhone());
        user.setRole(employeeApplication.getRole());
        user.setExperience(employeeApplication.getExperience());
        user.setPassword(employeeApplication.getPassword());
        userRepo.save(user);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully updated employee")
                .build();
    }
}


