package com.example.net_manage.domain;


import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "certification")
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "certifications")
    private List<NetworkEngineer> networkEngineers;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(UUID id) {
        this.id = id;

    }

    public void setName(String name) {
        this.name = name;
    }

        public List<NetworkEngineer> getNetworkEngineers() {
        return networkEngineers;
    }

    public void setNetworkEngineers(List<NetworkEngineer> networkEngineers) {
        this.networkEngineers = networkEngineers;
    }
}
