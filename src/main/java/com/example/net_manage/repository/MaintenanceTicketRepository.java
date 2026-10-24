package com.example.net_manage.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.net_manage.domain.MaintenanceTicket;

public interface MaintenanceTicketRepository extends JpaRepository<MaintenanceTicket, UUID> {
    
   
    List<MaintenanceTicket> findByLocalDate(String localDate);

    @Query ("SELECT m FROM MaintenanceTicket m WHERE m.localDate = :localDate")
    List<MaintenanceTicket> findByLocalDateAndStatus(@Param("localDate") String localDate, @Param("status") String status);

    List<MaintenanceTicket> findByNetworkDeviceId(UUID networkDeviceId);

    @Query ("SELECT m FROM MaintenanceTicket m WHERE m.networkDevice.id = :networkDeviceId")
    List<MaintenanceTicket> findByNetworkDeviceIdAndNetworkEngineerId(@Param("networkDeviceId") UUID networkDeviceId, @Param("engineerId") UUID engineerId);

    Optional<MaintenanceTicket> findByNetworkEngineerId(UUID engineerId);

    List<MaintenanceTicket> findByScheduledDate(String scheduledDate);

    @Query ("SELECT m FROM MaintenanceTicket m WHERE m.scheduledDate = :scheduledDate")
    List<MaintenanceTicket> findByScheduledDateAndStatus(@Param("scheduledDate") String scheduledDate, @Param("status") String status);

    List<MaintenanceTicket> findByStatus(String status);

    @Query ("SELECT m FROM MaintenanceTicket m WHERE m.status = :status")
    List<MaintenanceTicket> findByStatusAndNetworkDeviceId(@Param("status") String status, @Param("networkDeviceId") UUID networkDeviceId);
}
