package com.example.net_manage.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.net_manage.domain.MaintenanceTicket;
import com.example.net_manage.repository.MaintenanceTicketRepository;


@Service
public class MaintenanceTicketService {
    @Autowired
    private MaintenanceTicketRepository maintenanceTicketRepository;

    @Transactional
    public void delete(UUID id) {
        maintenanceTicketRepository.deleteById(id);
    }

    public List<MaintenanceTicket> findAll() {
        return maintenanceTicketRepository.findAll();
    }

    public Page<MaintenanceTicket> findAll(Pageable pageable) {
        return maintenanceTicketRepository.findAll(pageable);
    }

    public List<MaintenanceTicket> findAll(Sort sort) {
        return maintenanceTicketRepository.findAll(sort);
    }

    public Optional<MaintenanceTicket> findById(UUID id) {
        return maintenanceTicketRepository.findById(id);
    }

    @Transactional
    public MaintenanceTicket save(MaintenanceTicket maintenanceTicket) {
        return maintenanceTicketRepository.save(maintenanceTicket);
    }
}
