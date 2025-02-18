package java16.restproject18.repository;

import java16.restproject18.dto.response.CheckResponse;
import java16.restproject18.dto.response.WaiterCheck;
import java16.restproject18.entites.Cheque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ChequeRepo extends JpaRepository<Cheque, Long> {
    @Query("""
    SELECT new java16.restproject18.dto.response.CheckResponse(
        u.firstName,
        u.lastName, 
        m.name, 
        AVG(m.price), 
        (SUM(m.price) * 0.15), 
        (SUM(m.price) + (SUM(m.price) * 0.15)) 
    ) 
    FROM Cheque c
    JOIN c.user u
    JOIN c.menuItems m
    WHERE u.firstName = :nameUser
    GROUP BY u.firstName, u.lastName, m.name
""")
    List<CheckResponse> getAllCheckResponsesByChequeId(@Param("nameUser") String nameUser);

    @Query("""
    SELECT new java16.restproject18.dto.response.WaiterCheck(
        COALESCE(SUM(c.priceAverage), 0.0),
        :currentDate
    )
    FROM Cheque c
    JOIN c.user u
    WHERE u.id = :userId
    AND u.role = 'Waiter'
    AND c.creationDate = :currentDate
""")
    WaiterCheck getTotalAmountForWaiterToday(
            @Param("userId") Long userId,
            @Param("currentDate") LocalDate currentDate
    );

}
