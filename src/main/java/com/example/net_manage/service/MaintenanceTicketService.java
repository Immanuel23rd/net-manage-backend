package com.example.net_manage.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.net_manage.domain.MaintenanceTicket;
import com.example.net_manage.repository.MaintenanceTicketRepository;


@Service
public class MaintenanceTicketService {
    @Autowired 
    private MaintenanceTicketRepository MaintenanceTicketRepository;

    public MaintenanceTicketService(MaintenanceTicketRepository MaintenanceTicketRepository) {
        this.MaintenanceTicketRepository = MaintenanceTicketRepository;
    }

    public String save(MaintenanceTicket c) {
        MaintenanceTicketRepository.save(c);
        return "Maintenance ticket saved successfully";
    }

    public List<MaintenanceTicket> findAll() { return MaintenanceTicketRepository.findAll(); }

    public Optional<MaintenanceTicket> findById(UUID id) { return MaintenanceTicketRepository.findById(id); }

    public String update(UUID id, MaintenanceTicket incoming) {
        Optional<MaintenanceTicket> found = MaintenanceTicketRepository.findById(id);
        if (found.isEmpty()) return "The maintenance ticket with that id does not exist";
        MaintenanceTicket existing = found.get();      
        existing.setScheduledDate(incoming.getScheduledDate());
        existing.setIssue(incoming.getIssue());
        existing.setNetworkDevice(incoming.getNetworkDevice()); 
        existing.setNetworkEngineer(incoming.getNetworkEngineer());         
        MaintenanceTicketRepository.save(existing);
        return "Maintenance ticket updated successfully";
    }

    public String delete(UUID id) {
        if (!MaintenanceTicketRepository.existsById(id)) return "The maintenance ticket with that id does not exist";
        MaintenanceTicketRepository.deleteById(id);
        return "Maintenance ticket deleted successfully";
    }
}