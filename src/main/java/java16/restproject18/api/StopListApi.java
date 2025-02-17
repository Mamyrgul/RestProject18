package java16.restproject18.api;

import java16.restproject18.dto.request.CreateStopList;
import java16.restproject18.dto.response.SimpleResponse;
import java16.restproject18.dto.response.StopListResponse;
import java16.restproject18.service.StopListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stopList")
@RequiredArgsConstructor
public class StopListApi {
    private final StopListService stopListService;

    @GetMapping("/getAll")
    public List<StopListResponse> stopLists() {
        return stopListService.getAllStopLists();
    }

    @PostMapping("/create/{user}/{menuId}")
    public SimpleResponse createStopList(@PathVariable String user,
                                         @PathVariable Long menuId,
                                         @RequestBody CreateStopList createStopList) {
        return stopListService.createStopList(user, menuId, createStopList);
    }

    @DeleteMapping("/delete/{id}")
    public SimpleResponse deleteStopList(@PathVariable Long id) {
        return stopListService.deleteStopList(id);
    }

}
