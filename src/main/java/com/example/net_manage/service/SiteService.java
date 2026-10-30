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
    private SiteRepository siteRepository;

    public SiteService(SiteRepository SiteRepository) {
        this.siteRepository = SiteRepository;
    }

    public String save(Site c) {
        siteRepository.save(c);
        return "Site saved successfully";
    }

    public List<Site> findAll() { return siteRepository.findAll(); }

    public Optional<Site> findById(UUID id) { return siteRepository.findById(id); }

    public String update(UUID id, Site incoming) {
        Optional<Site> found = siteRepository.findById(id);
        if (found.isEmpty()) return "The site with that id does not exist";
        Site existing = found.get();      
        existing.setName(incoming.getName()); 
        existing.setSiteNumber(incoming.getSiteNumber());      
        siteRepository.save(existing);
        return "Site updated successfully";
    }

    public String delete(UUID id) {
        if (!siteRepository.existsById(id)) return "The site with that id does not exist";
        siteRepository.deleteById(id);
        return "Site deleted successfully";
    }

    // C2
    public List<Site> findFreeSites() { return siteRepository.findFreeSites(); }

}
    


