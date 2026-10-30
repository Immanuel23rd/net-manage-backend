package com.example.net_manage.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.net_manage.domain.MaintenanceLog;
import com.example.net_manage.domain.MaintenanceTicket;
import com.example.net_manage.domain.TicketStatus;
import com.example.net_manage.repository.MaintenanceLogRepository;
import com.example.net_manage.repository.MaintenanceTicketRepository;


import org.springframework.transaction.annotation.Transactional;


@Service
public class MaintenanceLogService {
    @Autowired private MaintenanceLogRepository maintenanceLogRepository;
    @Autowired private MaintenanceTicketRepository maintenanceTicketRepository;

    @Transactional
    public String save(MaintenanceLog log, UUID maintenanceTicketId) {

        // Rule 1: the ticket must exist 
        Optional<MaintenanceTicket> found = maintenanceTicketRepository.findById(maintenanceTicketId);
        if (found.isEmpty()) {
            return "The maintenance ticket with that id does not exist";
        }

        // Rule 2: logs are only for RESOLVED tickets 
        MaintenanceTicket ticket = found.get();
        if (ticket.getStatus() != TicketStatus.RESOLVED) {
            return "Maintenance logs are only for RESOLVED maintenance tickets";
        }

        log.setId(null);                          
        log.setLoggedDate(LocalDate.now());       
        log.setMaintenanceTicket(ticket);         
        maintenanceLogRepository.save(log);
        return "Maintenance log saved successfully";
    }


    //E2
    @Transactional(readOnly = true)
    public List<MaintenanceLog> findHistory(String serialNumber) {
    
        return maintenanceLogRepository.findHistoryBySerialNumber(serialNumber);
    }

    // E3
    @Transactional
    public String delete(UUID id) {
        if (!maintenanceLogRepository.existsById(id)) {
            return "The maintenance log with that id does not exist";
        }
        maintenanceLogRepository.deleteById(id);
        return "Maintenance log deleted successfully";
    }

}
