package com.example.net_manage.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.net_manage.domain.Certification;
import com.example.net_manage.domain.NetworkEngineer;
import com.example.net_manage.domain.Site;
import com.example.net_manage.repository.NetworkEngineerRepository;
import com.example.net_manage.repository.CertificationRepository;
import com.example.net_manage.repository.SiteRepository;

import jakarta.transaction.Transactional;


@Service
public class NetworkEngineerService {
  @Autowired 
    private NetworkEngineerRepository networkEngineerRepository;
  @Autowired 
    private CertificationRepository certificationRepository;
  @Autowired
    private SiteRepository siteRepository;

    public NetworkEngineerService(NetworkEngineerRepository NetworkEngineerRepository) {
        this.networkEngineerRepository = NetworkEngineerRepository;
    }

    // D3
    @Transactional
    public String addCertification(UUID engineerId, UUID certificationId) {

        Optional<NetworkEngineer> engineer = networkEngineerRepository.findById(engineerId);
        if (engineer.isEmpty()) return "The network engineer with that id does not exist";

        Optional<Certification> cert = certificationRepository.findById(certificationId);
        if (cert.isEmpty()) return "That certification does not exist";

        NetworkEngineer e = engineer.get();

   
        boolean alreadyHas = e.getCertifications().stream()
                .anyMatch(c -> c.getId().equals(certificationId));
        if (alreadyHas) return "The network engineer already has that certification";

        e.getCertifications().add(cert.get());
        networkEngineerRepository.save(e);
        return "Certification added to the network engineer";
    }

    // D4
    @Transactional
    public String changeSite(UUID engineerId, int siteNumber) {
    
        Optional<NetworkEngineer> engineer = networkEngineerRepository.findById(engineerId);
        if (engineer.isEmpty()) return "The network engineer with that id does not exist";
    
        Optional<Site> site = siteRepository.findBySiteNumber(siteNumber);
        if (site.isEmpty()) return "The site with that siteNumber does not exist";
    
        NetworkEngineer e = engineer.get();
        Site target = site.get();
    

        boolean alreadyThere = e.getSite() != null && e.getSite().getId().equals(target.getId());
    
        if (!alreadyThere && networkEngineerRepository.existsBySite(target)) {
            return "That site is taken by another network engineer";
        }
    

        e.setSite(target);
        networkEngineerRepository.save(e);
        return "Site changed successfully";
    }

    public String delete(UUID id) {
        if (!networkEngineerRepository.existsById(id)) return "The network engineer with that id does not exist";
        networkEngineerRepository.deleteById(id);
        return "Network engineer deleted successfully";
    }

    public List<NetworkEngineer> findAll() { return networkEngineerRepository.findAll(); }

    // C1
    public List<NetworkEngineer> findByCertification(String name) {
        return networkEngineerRepository.findByCertificationName(name);
    }

    public Optional<NetworkEngineer> findById(UUID id) { return networkEngineerRepository.findById(id); }

    public String save(NetworkEngineer c) {
        networkEngineerRepository.save(c);
        return "Network engineer saved successfully";
    }

    public String update(UUID id, NetworkEngineer incoming) {
    Optional<NetworkEngineer> found = networkEngineerRepository.findById(id);
    if (found.isEmpty()) return "The network engineer with that id does not exist";
    NetworkEngineer existing = found.get();      
    existing.setFirstName(incoming.getFirstName());  
    existing.setLastName(incoming.getLastName());
    existing.setEmail(incoming.getEmail());
    existing.setSite(incoming.getSite());       
    networkEngineerRepository.save(existing);
    return "Network engineer updated successfully";
}
}
