package com.example.net_manage.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.net_manage.domain.MaintenanceTicket;
import com.example.net_manage.domain.TicketStatus;
import com.example.net_manage.repository.MaintenanceTicketRepository;


@Service
public class MaintenanceTicketService {
    @Autowired 
    private MaintenanceTicketRepository maintenanceTicketRepository;

    public MaintenanceTicketService(MaintenanceTicketRepository MaintenanceTicketRepository) {
        this.maintenanceTicketRepository = MaintenanceTicketRepository;
    }

    public String save(MaintenanceTicket c) {
        maintenanceTicketRepository.save(c);
        return "Maintenance ticket saved successfully";
    }

    public List<MaintenanceTicket> findAll() { return maintenanceTicketRepository.findAll(); }

    public Optional<MaintenanceTicket> findById(UUID id) { return maintenanceTicketRepository.findById(id); }

    public String update(UUID id, MaintenanceTicket incoming) {
        Optional<MaintenanceTicket> found = maintenanceTicketRepository.findById(id);
        if (found.isEmpty()) return "The maintenance ticket with that id does not exist";
        MaintenanceTicket existing = found.get();      
        existing.setScheduledDate(incoming.getScheduledDate());
        existing.setIssue(incoming.getIssue());
        existing.setNetworkDevice(incoming.getNetworkDevice()); 
        existing.setNetworkEngineer(incoming.getNetworkEngineer());         
        maintenanceTicketRepository.save(existing);
        return "Maintenance ticket updated successfully";
    }

    public String delete(UUID id) {
        if (!maintenanceTicketRepository.existsById(id)) return "The maintenance ticket with that id does not exist";
        maintenanceTicketRepository.deleteById(id);
        return "Maintenance ticket deleted successfully";
    }

    // MaintenanceTicketService — B2
    public List<MaintenanceTicket> findByStatus(TicketStatus status) {
        return maintenanceTicketRepository.findByStatusOrderByScheduledDateAsc(status);
    }

    // MaintenanceTicketService — B3
    public List<MaintenanceTicket> findBetween(LocalDate start, LocalDate end) {
        return maintenanceTicketRepository.findByScheduledDateBetweenOrderByScheduledDateAsc(start, end);
    }
}