package java16.restproject18.service.serviceImpl;

import jakarta.transaction.Transactional;
import java16.restproject18.dto.request.CreateStopList;
import java16.restproject18.dto.response.SimpleResponse;
import java16.restproject18.dto.response.StopListResponse;
import java16.restproject18.entites.MenuItem;
import java16.restproject18.entites.StopList;
import java16.restproject18.enums.Role;
import java16.restproject18.repository.MenuItemRepo;
import java16.restproject18.repository.StopListRepo;
import java16.restproject18.service.StopListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StopListServiceImpl implements StopListService {
    private final StopListRepo stopListRepo;
    private final MenuItemRepo menuItemRepo;

    @Override
    public SimpleResponse createStopList(String user, Long menuId, CreateStopList createStopList) {
        if (!user.equals(Role.Chef.name()) && !user.equals(Role.Admin.name())) {
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.FORBIDDEN)
                    .message("Only administrators and chef can create a stop list")
                    .build();
        }

        MenuItem menuItem = menuItemRepo.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Меню с таким ID не найдено"));

        boolean exists = stopListRepo.existsByMenuItemNameAndStopDate(menuItem.getName(), LocalDate.now());
        if (exists) {
            throw new RuntimeException("Stop list for this dish already exists for today");
        }

        StopList stopList = new StopList();
        stopList.setReason(createStopList.getReason());
        stopList.setMenuItem(menuItem);
        stopListRepo.save(stopList);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.CREATED)
                .message("Successfully created a stop list")
                .build();
    }

    @Override
    public SimpleResponse deleteStopList(Long id) {
        stopListRepo.deleteById(id);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.ACCEPTED)
                .message("Successfully deleted")
                .build();
    }

    @Override
    public List<StopListResponse> getAllStopLists() {
        return stopListRepo.getAllStopLists();
    }
}
