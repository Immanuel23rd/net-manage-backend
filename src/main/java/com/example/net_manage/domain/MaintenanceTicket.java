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

    @Column (name = "LocalDate", nullable = false)
    private LocalDate localDate;

    @Column (name ="scheduledDate", nullable = false)
    private LocalDate scheduledDate;

    @Enumerated(EnumType.STRING)
    @Column (name = "TicketStatus", nullable = false)
    private TicketStatus.Status status;

    @ManyToOne
    @JoinColumn(name = "network_device_id", nullable = false)
    private NetworkDevice networkDevice;

    @ManyToOne
    @JoinColumn(name = "network_engineer_id", nullable = false)
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

    public TicketStatus.Status getStatus() {
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

    public void setStatus(TicketStatus.Status status) {
        this.status = status;
    }

}
