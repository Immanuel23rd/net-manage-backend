package com.example.net_manage.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.net_manage.domain.NetworkEngineer;


public interface NetworkEngineerRepository extends JpaRepository<NetworkEngineer, UUID> {

    List<NetworkEngineer> findByEmail(String email);

    @Query("SELECT n FROM NetworkEngineer n WHERE n.email = :email")
    Page<NetworkEngineer> findByEmail(@Param("email") String email, Pageable pageable);

    List<NetworkEngineer> findByFirstName(String firstName);

    Optional<NetworkEngineer> findById(UUID id);


    List<NetworkEngineer> findByLastName(String lastName);

    @Query("SELECT n FROM NetworkEngineer n WHERE n.name = :name")
    Page<NetworkEngineer> findByName(@Param("name") String name, Pageable pageable);
}
