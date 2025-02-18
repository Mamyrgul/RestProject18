package java16.restproject18.service;

import java16.restproject18.dto.request.CreateCheck;
import java16.restproject18.dto.response.CheckResponse;
import java16.restproject18.dto.response.SimpleResponse;
import java16.restproject18.dto.response.WaiterCheck;
import java16.restproject18.entites.Cheque;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ChequeService {
    SimpleResponse createCheque(String user,Long userId, CreateCheck check);
    List<CheckResponse> getAllCheckResponsesByChequeId(@Param("nameUser") String nameUser);
    WaiterCheck getTotalAmountForWaiterToday(@Param("userId") Long userId, @Param("currentDate") LocalDate currentDate);
    public SimpleResponse deleteCheque(String role, Long chequeId);
    public SimpleResponse updateCheque(String role, Cheque cheque);
}
