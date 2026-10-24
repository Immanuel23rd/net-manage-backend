package com.example.net_manage.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.net_manage.domain.Certification;

public interface CertificationRepository extends JpaRepository<Certification, UUID> {

    boolean existsByName(String name);

    boolean existsByNameAndNetworkEngineerId(String name, UUID networkEngineerId);

    @Query ("SELECT c FROM Certification c WHERE c.name = :name")
    Optional<Certification> findByName(@Param("name") String name);

    @Query ("SELECT c FROM Certification c WHERE c.name LIKE %:name%")
    List<Certification> findByNameContaining(@Param("name") String name);

    List<Certification> findByNetworkEngineerId(UUID networkEngineerId);

  
    List<Certification> findByNetworkEngineerIdAndCertificationName(UUID networkEngineerId, String certificationName);
}
