package com.example.net_manage.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.net_manage.domain.Site;

public interface SiteRepository extends JpaRepository<Site, Long> {


    @Query("SELECT s FROM Site s WHERE s.name = :name")
    Optional<Site> findByName(@Param("name") String name);

    @Query("SELECT s FROM Site s WHERE s.name LIKE %:name%")
    List<Site> findByNameContaining(@Param("name") String name);

    @Query("SELECT s FROM Site s WHERE s.id = :id")
    Optional<Site> findById(@Param("id") UUID id);

    @Query ("SELECT s FROM Site s WHERE s.siteNumber = :siteNumber")
    Optional<Site> findBySiteNumber(@Param("siteNumber") int siteNumber);
    
    @Query ("SELECT s FROM Site s WHERE s.siteNumber = :siteNumber")
    List<Site> findAllBySiteNumber(@Param("siteNumber") int siteNumber);

    @Query ("SELECT s FROM Site s WHERE s.name LIKE %:name%")
    Page<Site> findByNameContaining(@Param("name") String name, Pageable pageable);

    @Query ("SELECT s FROM Site s WHERE s.siteNumber = :siteNumber")
    Page<Site> findAllBySiteNumber(@Param("siteNumber") int siteNumber, Pageable pageable);
    
}
