package com.example.net_manage.domain;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "maintenance_ticket")
public class MaintenanceTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column (name = "localDate")
    private LocalDate localDate;

    @Column (name ="scheduledDate")
    private LocalDate scheduledDate;

    @Column (name ="issue")
    private String issue;

@Enumerated(EnumType.STRING)
    private TicketStatus status;

    @ManyToOne
    @JoinColumn(name = "network_device_id")
    private NetworkDevice networkDevice;

    @ManyToOne
    @JoinColumn(name = "network_engineer_id")
    private NetworkEngineer networkEngineer;

    public UUID getId() {
        return id;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public NetworkDevice getNetworkDevice() {
        return networkDevice;
    }

    public NetworkEngineer getNetworkEngineer() {
        return networkEngineer;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public void setNetworkDevice(NetworkDevice networkDevice) {
        this.networkDevice = networkDevice;
    }

    public void setNetworkEngineer(NetworkEngineer networkEngineer) {
        this.networkEngineer = networkEngineer;
    }

    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

}
