package java16.restproject18.service;

import java16.restproject18.dto.request.CreateStopList;
import java16.restproject18.dto.response.SimpleResponse;
import java16.restproject18.dto.response.StopListResponse;

import java.time.LocalDate;
import java.util.List;

public interface StopListService {
    SimpleResponse createStopList(String user,Long menuId, CreateStopList createStopList);
    SimpleResponse deleteStopList(Long id);
    List<StopListResponse> getAllStopLists();
}
