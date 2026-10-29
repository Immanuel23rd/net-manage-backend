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

import com.example.net_manage.domain.Site;
import com.example.net_manage.service.SiteService;

@RestController
@RequestMapping("/api/sites")
public class SiteController {
    @Autowired 
    private SiteService siteService;

    public SiteController(SiteService SiteService) {
        this.siteService = SiteService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Site c) {
        return ResponseEntity.status(HttpStatus.CREATED).body(siteService.save(c)); // 201
    }

    @GetMapping("/all")
    public List<Site> all() { return siteService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Object> byId(@PathVariable UUID id) {
        Optional<Site> found = siteService.findById(id);
        if (found.isEmpty())   
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The site with that id does not exist");
        return ResponseEntity.ok(found.get());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Site c) {
        String msg = siteService.update(id, c);
        if (msg.equals("The site with that id does not exist"))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        return ResponseEntity.ok(msg);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String msg = siteService.delete(id);
        if (msg.equals("The site with that id does not exist"))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        return ResponseEntity.ok(msg);
    }

    // SiteController — C2
    @GetMapping("/free")
    public List<Site> free() { return siteService.findFreeSites(); }
    }
