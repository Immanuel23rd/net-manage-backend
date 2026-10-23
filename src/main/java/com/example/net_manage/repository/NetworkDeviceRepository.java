package com.example.net_manage.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.net_manage.domain.NetworkDevice;


public interface NetworkDeviceRepository extends JpaRepository<NetworkDevice, UUID> {
    
    
    Optional<NetworkDevice> findByHostname(String hostname);

    Optional<NetworkDevice> findByIpAddress(String ipAddress);

    List<NetworkDevice> findByModel(String model);

    @Query ("SELECT n FROM NetworkDevice n WHERE n.name LIKE %:name%")
    List<NetworkDevice> findByNameContaining(@Param("name") String name);


    Optional<NetworkDevice> findBySerialNumber(String serialNumber);
    
}
