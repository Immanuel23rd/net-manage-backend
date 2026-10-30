package com.example.net_manage.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.net_manage.domain.MaintenanceLog;
import com.example.net_manage.domain.MaintenanceTicket;

public interface MaintenanceLogRepository extends JpaRepository<MaintenanceLog, UUID> {
    // E2 (JPQL)
    @Query("SELECT l FROM MaintenanceLog l " +
           "WHERE l.maintenanceTicket.networkDevice.serialNumber = :serialNumber " +
           "ORDER BY l.loggedDate DESC")
    List<MaintenanceLog> findHistoryBySerialNumber(@Param("serialNumber") String serialNumber);

    // E3 (DERIVED): blocks ticket deletion when logs exist
    boolean existsByMaintenanceTicket(MaintenanceTicket ticket);
}
