package com.example.net_manage.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.net_manage.domain.TicketStatus;
import com.example.net_manage.repository.MaintenanceLogRepository;
import com.example.net_manage.repository.MaintenanceTicketRepository;
import com.example.net_manage.repository.NetworkDeviceRepository;
import com.example.net_manage.repository.NetworkEngineerRepository;
import com.example.net_manage.repository.SiteRepository;

@Service
public class DashboardService {
    @Autowired private NetworkDeviceRepository networkDeviceRepository;
    @Autowired private NetworkEngineerRepository networkEngineerRepository;
    @Autowired private SiteRepository siteRepository;
    @Autowired private MaintenanceTicketRepository maintenanceTicketRepository;
    @Autowired private MaintenanceLogRepository maintenanceLogRepository;

    public Map<String, Long> summary() {
        Map<String, Long> summary = new LinkedHashMap<>();
        summary.put("devices", networkDeviceRepository.count());              
        summary.put("engineers", networkEngineerRepository.count());
        summary.put("freeSites", (long) siteRepository.findFreeSites().size()); 
        summary.put("ticketsToday",                                            
                maintenanceTicketRepository.countByScheduledDate(LocalDate.now()));
        summary.put("opened",
                maintenanceTicketRepository.countByStatus(TicketStatus.OPENED));
        summary.put("logs", maintenanceLogRepository.count());
        return summary;
    }
}
