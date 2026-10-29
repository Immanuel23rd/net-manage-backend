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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.net_manage.domain.NetworkDevice;
import com.example.net_manage.service.NetworkDeviceService;
import com.example.net_manage.service.NetworkEngineerService;

@RestController
@RequestMapping("/api/network-devices")
public class NetworkDeviceController {
    @Autowired 
    private NetworkDeviceService networkDeviceService;
    @Autowired
    private NetworkEngineerService networkEngineerService;

    public NetworkDeviceController(NetworkDeviceService NetworkDeviceService) {
        this.networkDeviceService = NetworkDeviceService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody NetworkDevice c) {
        return ResponseEntity.status(HttpStatus.CREATED).body(networkDeviceService.save(c)); // 201
    }

    @GetMapping("/all")
    public List<NetworkDevice> all() { return networkDeviceService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Object> byId(@PathVariable UUID id) {
        Optional<NetworkDevice> found = networkDeviceService.findById(id);
        if (found.isEmpty())   
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The network device with that id does not exist");
        return ResponseEntity.ok(found.get());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody NetworkDevice c) {
        String msg = networkDeviceService.update(id, c);
        if (msg.equals("The network device with that id does not exist"))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        return ResponseEntity.ok(msg);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String msg = networkDeviceService.delete(id);
        if (msg.equals("The network device with that id does not exist"))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        return ResponseEntity.ok(msg);
    }

    // NetworkDeviceController — B1
    @GetMapping("/by-model")
    public List<NetworkDevice> byModel(@RequestParam String model) {
        return networkDeviceService.findByModel(model);
    }

    // NetworkDeviceController — C4 (needs BOTH services injected with @Autowired)
   @GetMapping("/of-network-engineer/{networkEngineerId}")
   public ResponseEntity<Object> ofEngineer(@PathVariable UUID networkEngineerId) {
    // Check the engineer first: an unknown id must give 404, not an empty list.
       if (networkEngineerService.findById(networkEngineerId).isEmpty())
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("The network engineer with that id does not exist");
       return ResponseEntity.ok(networkDeviceService.findByEngineer(networkEngineerId));
    }
}
