package com.example.net_manage.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.net_manage.domain.MaintenanceLog;
import com.example.net_manage.service.MaintenanceLogService;


@RestController
@RequestMapping("/api/maintenance-logs")
public class MaintenanceLogController {
    @Autowired private MaintenanceLogService maintenanceLogService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody MaintenanceLog log,
                                       @RequestParam UUID maintenanceTicketId) {
        String msg = maintenanceLogService.save(log, maintenanceTicketId);

        if (msg.equals("The maintenance ticket with that id does not exist"))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);          // 404
        if (msg.equals("Maintenance logs are only for RESOLVED maintenance tickets"))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);        // 400
        return ResponseEntity.status(HttpStatus.CREATED).body(msg);                // 201
    }


    // E2
    @GetMapping("/network-device/{serialNumber}")
    public List<MaintenanceLog> history(@PathVariable String serialNumber) {
        return maintenanceLogService.findHistory(serialNumber);
    }
    
    // E3   
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String msg = maintenanceLogService.delete(id);
        if (msg.equals("The maintenance log with that id does not exist"))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);          // 404
        return ResponseEntity.ok(msg);                                             // 200
    }
}