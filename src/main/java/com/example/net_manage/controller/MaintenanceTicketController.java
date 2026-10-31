package com.example.net_manage.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.net_manage.domain.MaintenanceTicket;
import com.example.net_manage.domain.TicketStatus;
import com.example.net_manage.service.MaintenanceTicketService;

@RestController
@RequestMapping("/api/maintenance-tickets")
public class MaintenanceTicketController {
    @Autowired 
    private MaintenanceTicketService maintenanceTicketService;

    public MaintenanceTicketController(MaintenanceTicketService MaintenanceTicketService) {
        this.maintenanceTicketService = MaintenanceTicketService;
    }

    //A2
    //@PostMapping("/save")
    //public ResponseEntity<String> save(@RequestBody MaintenanceTicket c) {
    //    return ResponseEntity.status(HttpStatus.CREATED).body(maintenanceTicketService.save(c)); // 201
    //}

    // D1
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody MaintenanceTicket ticket) {
        String msg = maintenanceTicketService.save(ticket);

    
        if (msg.equals("Maintenance ticket date cannot be in the past"))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);            // 400
        if (msg.equals("Engineer already has a ticket scheduled on that date"))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(msg);               // 409
        return ResponseEntity.status(HttpStatus.CREATED).body(msg);                    // 201
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

    // A2
    //@DeleteMapping("/delete/{id}")
    //public ResponseEntity<String> delete(@PathVariable UUID id) {
    //    String msg = maintenanceTicketService.delete(id);
    //    if (msg.equals("The maintenance ticket with that id does not exist"))
    //        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
    //    return ResponseEntity.ok(msg);
    //}


    // B2
   @GetMapping("/by-status")
   public List<MaintenanceTicket> byStatus(@RequestParam TicketStatus status) {
        return maintenanceTicketService.findByStatus(status);
    }

   
     // B3
    @GetMapping("/between")
    public List<MaintenanceTicket> between(@RequestParam String start, @RequestParam String end) {
        return maintenanceTicketService.findBetween(LocalDate.parse(start), LocalDate.parse(end));
    }

    // D2
    @PatchMapping("/{id}/status")
    public ResponseEntity<String> changeStatus(@PathVariable UUID id,
                                           @RequestParam TicketStatus status) {
        String msg = maintenanceTicketService.changeStatus(id, status);

        if (msg.equals("The maintenance ticket with that id does not exist"))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);              // 404
        if (msg.startsWith("Cannot change status"))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);            // 400
        return ResponseEntity.ok(msg);                                                 // 200
    }

    // E3
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String msg = maintenanceTicketService.delete(id);

        if (msg.equals("The maintenance ticket with that id does not exist"))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);              // 404
        if (msg.equals("That maintenance ticket has maintenance logs, delete them first"))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(msg);               // 409
        return ResponseEntity.ok(msg);                                                 // 200
    }


    // F1
    @GetMapping("/stats/by-status")
    public List<Object[]> statsByStatus() {
        return maintenanceTicketService.countPerStatus();
    }

    // F2
    @PatchMapping("/cancel-day")
    public ResponseEntity<String> cancelDay(@RequestParam UUID networkEngineerId,
                                        @RequestParam String date) {
        return ResponseEntity.ok(
                maintenanceTicketService.cancelDay(networkEngineerId, LocalDate.parse(date)));
    }

    // Pagination
    @GetMapping("/page")
    public Page<MaintenanceTicket> page(
           @RequestParam(defaultValue = "0") int page,
           @RequestParam(defaultValue = "5") int size,
           @RequestParam(defaultValue = "scheduledDate,desc") String sort) {

        String[] parts = sort.split(",");
        Sort.Direction direction = parts.length > 1
                ? Sort.Direction.fromString(parts[1])   
                : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, parts[0]));


        return maintenanceTicketService.findAll(pageable);
    }
}
