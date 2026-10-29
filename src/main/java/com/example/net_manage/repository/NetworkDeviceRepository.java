package com.example.net_manage.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.net_manage.domain.NetworkDevice;


public interface NetworkDeviceRepository extends JpaRepository<NetworkDevice, UUID> {
    
    
    // B1 (DERIVED): IgnoreCase + OrderBy
    List<NetworkDevice> findByModelIgnoreCaseOrderByHostnameAsc(String model);

    Optional<NetworkDevice> findBySerialNumber(String serialNumber);


    // C4 (JPQL):
    @Query("SELECT DISTINCT t.networkDevice FROM MaintenanceTicket t " +
           "WHERE t.networkEngineer.id = :engineerId")
    List<NetworkDevice> findByEngineer(@Param("engineerId") UUID engineerId); 
}
