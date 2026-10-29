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

import com.example.net_manage.domain.Certification;
import com.example.net_manage.service.CertificationService;

@RestController
@RequestMapping("/api/certifications")
public class CertificationController {
    @Autowired 
    private CertificationService certificationService;

    public CertificationController(CertificationService certificationService) {
        this.certificationService = certificationService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Certification c) {
        return ResponseEntity.status(HttpStatus.CREATED).body(certificationService.save(c)); // 201
    }

    @GetMapping("/all")
    public List<Certification> all() { return certificationService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Object> byId(@PathVariable UUID id) {
        Optional<Certification> found = certificationService.findById(id);
        if (found.isEmpty())   
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The certification with that id does not exist");
        return ResponseEntity.ok(found.get());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Certification c) {
        String msg = certificationService.update(id, c);
        if (msg.equals("The certification with that id does not exist"))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        return ResponseEntity.ok(msg);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String msg = certificationService.delete(id);
        if (msg.equals("The certification with that id does not exist"))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        return ResponseEntity.ok(msg);
    }

    // CertificationController — C3
    @GetMapping("/unused")
    public List<Certification> unused() { return certificationService.findUnused(); }
}
