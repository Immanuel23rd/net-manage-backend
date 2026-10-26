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

import com.example.net_manage.domain.NetworkEngineer;
import com.example.net_manage.service.NetworkEngineerService;

@RestController
@RequestMapping("/api/network-engineers")
public class NetworkEngineerController {
    @Autowired 
    private NetworkEngineerService networkEngineerService;

    public NetworkEngineerController(NetworkEngineerService NetworkEngineerService) {
        this.networkEngineerService = NetworkEngineerService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody NetworkEngineer c) {
        return ResponseEntity.status(HttpStatus.CREATED).body(networkEngineerService.save(c)); // 201
    }

    @GetMapping("/all")
    public List<NetworkEngineer> all() { return networkEngineerService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Object> byId(@PathVariable UUID id) {
        Optional<NetworkEngineer> found = networkEngineerService.findById(id);
        if (found.isEmpty())   
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The network engineer with that id does not exist");
        return ResponseEntity.ok(found.get());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody NetworkEngineer c) {
        String msg = networkEngineerService.update(id, c);
        if (msg.equals("The network engineer with that id does not exist"))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        return ResponseEntity.ok(msg);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String msg = networkEngineerService.delete(id);
        if (msg.equals("The network engineer with that id does not exist"))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        return ResponseEntity.ok(msg);
    }
}
