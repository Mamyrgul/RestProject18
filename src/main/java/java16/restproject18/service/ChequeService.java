package java16.restproject18.service;

import java16.restproject18.dto.request.CreateCheck;
import java16.restproject18.dto.response.CheckResponse;
import java16.restproject18.dto.response.SimpleResponse;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChequeService {
    SimpleResponse createCheque(String user,Long userId, CreateCheck check);
    List<CheckResponse> getAllCheckResponsesByChequeId(@Param("nameUser") String nameUser);

}
