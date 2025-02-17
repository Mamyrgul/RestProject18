package java16.restproject18.repository;

import java16.restproject18.dto.response.StopListResponse;
import java16.restproject18.entites.StopList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StopListRepo extends JpaRepository<StopList, Long> {
    @Query("""
select new java16.restproject18.dto.response.StopListResponse(s.reason,s.stopDate) from StopList s
""")
    List<StopListResponse> getAllStopLists();
    boolean existsByMenuItemNameAndStopDate(String menuItemName, LocalDate stopDate);
}
