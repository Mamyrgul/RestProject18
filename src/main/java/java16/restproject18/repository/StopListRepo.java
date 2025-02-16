package java16.restproject18.repository;

import java16.restproject18.entites.StopList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StopListRepo extends JpaRepository<StopList, Long> {
}
