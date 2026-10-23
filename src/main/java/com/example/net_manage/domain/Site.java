package com.example.net_manage.domain;


import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "site")
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "siteNumber", nullable = false)
    private int siteNumber;

    public UUID getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public int getSiteNumber() {
        return siteNumber;
    }

    public void setId(UUID id) {
        this.id = id;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSiteNumber(int siteNumber) {
        this.siteNumber = siteNumber;
    }
}
