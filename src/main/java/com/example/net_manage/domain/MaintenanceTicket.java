package com.example.net_manage.domain;

import java.util.UUID;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

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

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public TicketStatus.Status getStatus() {
        return status;
    }

    public void setStatus(TicketStatus.Status status) {
        this.status = status;
    }

    public NetworkDevice getNetworkDevice() {
        return networkDevice;
    }

    public void setNetworkDevice(NetworkDevice networkDevice) {
        this.networkDevice = networkDevice;
    }

    public NetworkEngineer getNetworkEngineer() {
        return networkEngineer;
    }

    public void setNetworkEngineer(NetworkEngineer networkEngineer) {
        this.networkEngineer = networkEngineer;
    }

}
