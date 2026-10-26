package com.example.net_manage.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.net_manage.domain.Certification;

public interface CertificationRepository extends JpaRepository<Certification, UUID> {
    @Query("SELECT c FROM Certification c WHERE c.networkEngineers IS EMPTY")
    List<Certification> findUnused();
}
