package com.example.net_manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.net_manage.domain.NetworkEngineer;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;


public interface NetworkEngineerRepository extends JpaRepository<NetworkEngineer, UUID> {

    Optional<NetworkEngineer> findById(UUID id);

    List<NetworkEngineer> findByFirstName(String firstName);

    List<NetworkEngineer> findByLastName(String lastName);

    List<NetworkEngineer> findByEmail(String email);


    @Query("SELECT n FROM NetworkEngineer n WHERE n.name = :name")
    Page<NetworkEngineer> findByName(@Param("name") String name, Pageable pageable);

    @Query("SELECT n FROM NetworkEngineer n WHERE n.email = :email")
    Page<NetworkEngineer> findByEmail(@Param("email") String email, Pageable pageable);
}
