package com.example.net_manage.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.net_manage.domain.Certification;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;

public interface CertificationRepository extends JpaRepository<Certification, UUID> {
    
    List<Certification> findByEngineerId(UUID engineerId);
    
    Optional<Certification> findByFirstName(String firstName);

    Optional<Certification> findByLastName(String lastName);

    @Query("SELECT c FROM Certification c WHERE c.engineer.id = :engineerId")
    Page<Certification> findByEngineerId(@Param("engineerId") UUID engineerId, org.springframework.data.domain.Pageable pageable);

    @Query ("SELECT c FROM Certification c WHERE c.name = :name")
    Optional<Certification> findByName(@Param("name") String name);

    @Query ("SELECT c FROM Certification c WHERE c.name LIKE %:name%")
    List<Certification> findByNameContaining(@Param("name") String name);
    
}
