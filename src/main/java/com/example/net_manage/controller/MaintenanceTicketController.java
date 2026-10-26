package com.example.net_manage.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.net_manage.domain.MaintenanceTicket;
import com.example.net_manage.service.MaintenanceTicketService;

@RestController
@RequestMapping("/api/maintenance-tickets")
public class MaintenanceTicketController {
    @Autowired 
    private MaintenanceTicketService maintenanceTicketService;

    public MaintenanceTicketController(MaintenanceTicketService MaintenanceTicketService) {
        this.maintenanceTicketService = MaintenanceTicketService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody MaintenanceTicket c) {
        return ResponseEntity.status(HttpStatus.CREATED).body(maintenanceTicketService.save(c)); // 201
    }

    @GetMapping("/all")
    public List<MaintenanceTicket> all() { return maintenanceTicketService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Object> byId(@PathVariable UUID id) {
        Optional<MaintenanceTicket> found = maintenanceTicketService.findById(id);
        if (found.isEmpty())   
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The maintenance ticket with that id does not exist");
        return ResponseEntity.ok(found.get());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody MaintenanceTicket c) {
        String msg = maintenanceTicketService.update(id, c);
        if (msg.equals("The maintenance ticket with that id does not exist"))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        return ResponseEntity.ok(msg);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String msg = maintenanceTicketService.delete(id);
        if (msg.equals("The maintenance ticket with that id does not exist"))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        return ResponseEntity.ok(msg);
    }
}
