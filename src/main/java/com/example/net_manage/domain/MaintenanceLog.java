package com.example.net_manage.domain;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// E1
@Entity
@Table(name = "maintenance_log")
public class MaintenanceLog {
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID) 
    private UUID id;
    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column (name = "action")
    private String action;
    
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Column (name = "notes")
    private String notes;
    
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Column (name = "minutesSpent")
    private int minutesSpent;
    
    public int getMinutesSpent() {
        return minutesSpent;
    }

    public void setMinutesSpent(int minutesSpent) {
        this.minutesSpent = minutesSpent;
    }

    @Column (name = "loggedDate")
    private LocalDate loggedDate;

    public LocalDate getLoggedDate() {
        return loggedDate;
    }

    public void setLoggedDate(LocalDate loggedDate) {
        this.loggedDate = loggedDate;
    }

    // Many logs -> one ticket.
    @ManyToOne 
    @JoinColumn(name = "maintenance_ticket_id")
    private MaintenanceTicket maintenanceTicket;

    public MaintenanceTicket getMaintenanceTicket() {
        return maintenanceTicket;
    }

    public void setMaintenanceTicket(MaintenanceTicket maintenanceTicket) {
        this.maintenanceTicket = maintenanceTicket;
    }

    
}
