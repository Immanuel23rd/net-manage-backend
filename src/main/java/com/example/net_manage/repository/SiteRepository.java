package com.example.net_manage.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.net_manage.domain.Site;

public interface SiteRepository extends JpaRepository<Site, UUID> {
    
    Optional<Site> findBySiteNumber(int siteNumber);
   
   // C2 (JPQL)
    @Query("SELECT s FROM Site s WHERE NOT EXISTS " +
           "(SELECT e FROM NetworkEngineer e WHERE e.site = s) ORDER BY s.siteNumber")
    List<Site> findFreeSites();
}
