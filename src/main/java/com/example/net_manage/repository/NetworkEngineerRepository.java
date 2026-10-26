package com.example.net_manage.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.net_manage.domain.NetworkEngineer;
import com.example.net_manage.domain.Site;


public interface NetworkEngineerRepository extends JpaRepository<NetworkEngineer, UUID> {

    @Query("SELECT DISTINCT e FROM NetworkEngineer e JOIN e.certifications c " +
           "WHERE LOWER(c.name) = LOWER(:name)")
    List<NetworkEngineer> findByCertificationName(@Param("name") String name);

  
    boolean existsBySite(Site site);
}
