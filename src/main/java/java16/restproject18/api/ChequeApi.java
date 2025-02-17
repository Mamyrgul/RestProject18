package java16.restproject18.api;

import java16.restproject18.dto.request.CreateCheck;
import java16.restproject18.dto.response.CheckResponse;
import java16.restproject18.dto.response.SimpleResponse;
import java16.restproject18.service.ChequeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/check")
@RequiredArgsConstructor
public class ChequeApi {
    private final ChequeService chequeService;
    @GetMapping("/getByName/{waiterName}")
    List<CheckResponse> getByName(@PathVariable String waiterName) {
        return chequeService.getAllCheckResponsesByChequeId(waiterName);
    }
    @PostMapping("/create/{role}/{userId}")
    SimpleResponse create(@PathVariable String role, @PathVariable Long userId, @RequestBody CreateCheck check) {
        return chequeService.createCheque(role,userId,check);
    }
}
