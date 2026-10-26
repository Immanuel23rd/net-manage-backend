package com.example.net_manage.domain;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "network_device")
public class NetworkDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "hostname")
    private String hostname;

    @Column(name = "ipAddress")
    private String ipAddress;

    @Column (name = "model")
    private String model;
    
    @Column (name = "serialNumber", unique = true)
    private String serialNumber;

    public String getHostname() {
        return hostname;
    }

    public UUID getId() {
        return id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getModel() {
        return model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
