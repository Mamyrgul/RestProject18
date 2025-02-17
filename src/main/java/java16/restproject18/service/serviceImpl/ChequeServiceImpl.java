package java16.restproject18.service.serviceImpl;

import jakarta.transaction.Transactional;
import java16.restproject18.dto.request.CreateCheck;
import java16.restproject18.dto.response.CheckResponse;
import java16.restproject18.dto.response.SimpleResponse;
import java16.restproject18.dto.response.WaiterCheck;
import java16.restproject18.entites.Cheque;
import java16.restproject18.entites.User;
import java16.restproject18.enums.Role;
import java16.restproject18.repository.ChequeRepo;
import java16.restproject18.repository.UserRepo;
import java16.restproject18.service.ChequeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ChequeServiceImpl implements ChequeService {
    private final ChequeRepo chequeRepo;
    private final UserRepo userRepo;

    @Override
    public SimpleResponse createCheque(String user,Long userId, CreateCheck check) {
        if (!user.equals(Role.Admin.name()) && !user.equals(Role.Waiter.name())) {
            return SimpleResponse.builder().httpStatus(HttpStatus.ACCEPTED)
                    .message("only admin and waiter can create check")
                    .build();
        }
      User user1 = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
        Cheque cheque= new Cheque();
        cheque.setUser(user1);
        cheque.setPriceAverage(check.getAveragePrice());
        chequeRepo.save(cheque);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.ACCEPTED)
                .message("successfully created cheque")
                .build();
    }

    @Override
    public List<CheckResponse> getAllCheckResponsesByChequeId(String nameUser) {
        return chequeRepo.getAllCheckResponsesByChequeId(nameUser);
    }

    @Override
    public WaiterCheck getTotalAmountForWaiterToday(Long userId, LocalDate currentDate) {
        return chequeRepo.getTotalAmountForWaiterToday(userId, currentDate);
    }

    public SimpleResponse deleteCheque(String role, Long chequeId) {
       if (!role.equals(Role.Chef.name()) && !role.equals(Role.Waiter.name())) {
           throw new RuntimeException("only admin can delete check");
       }
        chequeRepo.deleteById(chequeId);
       return SimpleResponse.builder().httpStatus(HttpStatus.ACCEPTED).message("successfully deleted").build();
    }

    public SimpleResponse updateCheque(String role,Cheque cheque) {
        if (!role.equals(Role.Chef.name()) && !role.equals(Role.Waiter.name())) {
            throw new RuntimeException("only admin can delete check");
        }
        Cheque cheque1 = new Cheque();
        cheque1.setPriceAverage(cheque.getPriceAverage());
        chequeRepo.save(cheque1);
        return SimpleResponse.builder().httpStatus(HttpStatus.ACCEPTED).message("successfully updated").build();
    }


}

