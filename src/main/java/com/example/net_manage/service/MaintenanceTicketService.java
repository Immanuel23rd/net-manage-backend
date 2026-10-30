package com.example.net_manage.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    //A2
    //public String save(MaintenanceTicket c) {
    //    maintenanceTicketRepository.save(c);
    //    return "Maintenance ticket saved successfully";
    //}

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

    // B2
    public List<MaintenanceTicket> findByStatus(TicketStatus status) {
        return maintenanceTicketRepository.findByStatusOrderByScheduledDateAsc(status);
    }

    // B3
    public List<MaintenanceTicket> findBetween(LocalDate start, LocalDate end) {
        return maintenanceTicketRepository.findByScheduledDateBetweenOrderByScheduledDateAsc(start, end);
    }

    // D1
    @Transactional
    public String save(MaintenanceTicket ticket) {

    // Rule 1: no tickets in the past.
    if (ticket.getScheduledDate().isBefore(LocalDate.now())) {
        return "Maintenance ticket date cannot be in the past";
    }

    // Rule 2: the engineer must be free that day. "Free" means: no ticket on that
    boolean busy = maintenanceTicketRepository
            .existsByNetworkEngineerIdAndScheduledDateAndStatusNot(
                    ticket.getNetworkEngineer().getId(),
                    ticket.getScheduledDate(),
                    TicketStatus.ABORTED);
    if (busy) {
        return "Engineer already has a ticket scheduled on that date";
    }

    // Rule 3: a new ticket always starts OPENED, whatever the JSON body said.
    ticket.setStatus(TicketStatus.OPENED);
    maintenanceTicketRepository.save(ticket);
    return "Maintenance ticket saved successfully";
    }

    // D2
    @Transactional
    public String changeStatus(UUID id, TicketStatus newStatus) {

        Optional<MaintenanceTicket> found = maintenanceTicketRepository.findById(id);
        if (found.isEmpty()) {
            return "The maintenance ticket with that id does not exist";
        }

        MaintenanceTicket ticket = found.get();
        TicketStatus current = ticket.getStatus();


        boolean allowed =
                (current == TicketStatus.OPENED
                        && (newStatus == TicketStatus.IN_PROGRESS || newStatus == TicketStatus.ABORTED))
             || (current == TicketStatus.IN_PROGRESS
                        && (newStatus == TicketStatus.RESOLVED || newStatus == TicketStatus.ABORTED));

        if (!allowed) {
            // Real enum names in the message, e.g. "Cannot change status from RESOLVED to OPENED"
            return "Cannot change status from " + current + " to " + newStatus;
        }

        ticket.setStatus(newStatus);
        maintenanceTicketRepository.save(ticket);
        return "Maintenance ticket status updated successfully";
    }

}