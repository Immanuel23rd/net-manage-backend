package com.example.net_manage.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.net_manage.domain.MaintenanceTicket;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;

public interface MaintenanceTicketRepository extends JpaRepository<MaintenanceTicket, UUID> {
    
    Optional<MaintenanceTicket> findByNetworkEngineerId(UUID engineerId);
    
    List<MaintenanceTicket> findByStatus(String status);

    List<MaintenanceTicket> findByScheduledDate(String scheduledDate);

    List<MaintenanceTicket> findByLocalDate(String localDate);

    List<MaintenanceTicket> findByNetworkDeviceId(UUID networkDeviceId);

    @Query("SELECT m FROM MaintenanceTicket m WHERE m.engineer.id = :engineerId")
    Page<MaintenanceTicket> findByEngineerId(@Param("engineerId") UUID engineerId, org.springframework.data.domain.Pageable pageable);

    @Query ("SELECT m FROM MaintenanceTicket m WHERE m.status = :status")
    List<MaintenanceTicket> findByStatusAndNetworkDeviceId(@Param("status") String status, @Param("networkDeviceId") UUID networkDeviceId);

    @Query ("SELECT m FROM MaintenanceTicket m WHERE m.scheduledDate = :scheduledDate")
    List<MaintenanceTicket> findByScheduledDateAndStatus(@Param("scheduledDate") String scheduledDate, @Param("status") String status);

    @Query ("SELECT m FROM MaintenanceTicket m WHERE m.localDate = :localDate")
    List<MaintenanceTicket> findByLocalDateAndStatus(@Param("localDate") String localDate, @Param("status") String status);

    @Query ("SELECT m FROM MaintenanceTicket m WHERE m.networkDevice.id = :networkDeviceId")
    List<MaintenanceTicket> findByNetworkDeviceIdAndNetworkEngineerId(@Param("networkDeviceId") UUID networkDeviceId, @Param("engineerId") UUID engineerId);
}
