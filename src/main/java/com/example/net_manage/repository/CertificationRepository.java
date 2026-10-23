package com.example.net_manage.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.net_manage.domain.Certification;

public interface CertificationRepository extends JpaRepository<Certification, UUID> {

    boolean existsByName(String name);

    boolean existsByNameAndNetworkEngineerId(String name, UUID networkEngineerId);

    @Query ("SELECT c FROM Certification c WHERE c.name = :name")
    Page<Certification> findAllByName(@Param("name") String name, Pageable pageable);

    @Query ("SELECT c FROM Certification c WHERE c.name = :name")
    Optional<Certification> findByName(@Param("name") String name);

    @Query ("SELECT c FROM Certification c WHERE c.name LIKE %:name%")
    List<Certification> findByNameContaining(@Param("name") String name);

    @Query ("SELECT c FROM Certification c WHERE c.name LIKE %:name%")
    Page<Certification> findByNameContaining(@Param("name") String name, Pageable pageable);

    List<Certification> findByNetworkEngineerId(UUID networkEngineerId);

    @Query("SELECT c FROM Certification c WHERE c.networkEngineer.id = :networkEngineerId")
    Page<Certification> findByNetworkEngineerId(@Param("networkEngineerId") UUID networkEngineerId, org.springframework.data.domain.Pageable pageable);

    List<Certification> findByNetworkEngineerIdAndCertificationName(UUID networkEngineerId, String certificationName);
}
