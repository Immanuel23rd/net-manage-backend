package com.example.net_manage.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.net_manage.domain.NetworkDevice;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface NetworkDeviceRepository extends JpaRepository<NetworkDevice, UUID> {
    
    
    List<NetworkDevice> findByModel(String model);

    Optional<NetworkDevice> findBySerialNumber(String serialNumber);

    Optional<NetworkDevice> findByIpAddress(String ipAddress);

    Optional<NetworkDevice> findByHostname(String hostname);


    @Query ("SELECT n FROM NetworkDevice n WHERE n.name LIKE %:name%")
    List<NetworkDevice> findByNameContaining(@Param("name") String name);
    
}
