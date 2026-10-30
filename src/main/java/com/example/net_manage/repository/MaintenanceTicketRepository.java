package com.example.net_manage.repository;

import java.util.List;
import java.util.UUID;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.net_manage.domain.MaintenanceTicket;
import com.example.net_manage.domain.TicketStatus;

public interface MaintenanceTicketRepository extends JpaRepository<MaintenanceTicket, UUID> {
    
    // B2 (DERIVED)
    List<MaintenanceTicket> findByStatusOrderByScheduledDateAsc(TicketStatus status);

    // B3 (DERIVED)
    List<MaintenanceTicket> findByScheduledDateBetweenOrderByScheduledDateAsc(LocalDate start, LocalDate end);

    // D1 (DERIVED)
    boolean existsByNetworkEngineerIdAndScheduledDateAndStatusNot(
            UUID engineerId, LocalDate date, TicketStatus status);
    // F1
    @Query("SELECT t.status, COUNT(t) FROM MaintenanceTicket t GROUP BY t.status")
    List<Object[]> countPerStatus();

    // F2
    @Modifying
    @Query("UPDATE MaintenanceTicket t SET t.status = com.example.net_manage.domain.TicketStatus.ABORTED " +
           "WHERE t.networkEngineer.id = :engineerId AND t.scheduledDate = :date " +
           "AND t.status <> com.example.net_manage.domain.TicketStatus.RESOLVED")
    int cancelDay(@Param("engineerId") UUID engineerId, @Param("date") LocalDate date);
    }
