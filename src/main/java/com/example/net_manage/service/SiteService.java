package com.example.net_manage.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.net_manage.domain.Site;
import com.example.net_manage.repository.SiteRepository;

@Service
public class SiteService {
  @Autowired 
    private SiteRepository SiteRepository;

    public SiteService(SiteRepository SiteRepository) {
        this.SiteRepository = SiteRepository;
    }

    public String save(Site c) {
        SiteRepository.save(c);
        return "Site saved successfully";
    }

    public List<Site> findAll() { return SiteRepository.findAll(); }

    public Optional<Site> findById(UUID id) { return SiteRepository.findById(id); }

    public String update(UUID id, Site incoming) {
        Optional<Site> found = SiteRepository.findById(id);
        if (found.isEmpty()) return "The site with that id does not exist";
        Site existing = found.get();      
        existing.setName(incoming.getName());      
        SiteRepository.save(existing);
        return "Site updated successfully";
    }

    public String delete(UUID id) {
        if (!SiteRepository.existsById(id)) return "The site with that id does not exist";
        SiteRepository.deleteById(id);
        return "Site deleted successfully";
    }

}
    


