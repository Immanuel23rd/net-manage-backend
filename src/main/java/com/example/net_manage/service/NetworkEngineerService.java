package com.example.net_manage.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.net_manage.domain.NetworkEngineer;
import com.example.net_manage.repository.NetworkEngineerRepository;


@Service
public class NetworkEngineerService {
  @Autowired 
    private NetworkEngineerRepository networkEngineerRepository;

    public NetworkEngineerService(NetworkEngineerRepository NetworkEngineerRepository) {
        this.networkEngineerRepository = NetworkEngineerRepository;
    }

    public String save(NetworkEngineer c) {
        networkEngineerRepository.save(c);
        return "Network engineer saved successfully";
    }

    public List<NetworkEngineer> findAll() { return networkEngineerRepository.findAll(); }

    public Optional<NetworkEngineer> findById(UUID id) { return networkEngineerRepository.findById(id); }

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

    public String delete(UUID id) {
        if (!networkEngineerRepository.existsById(id)) return "The network engineer with that id does not exist";
        networkEngineerRepository.deleteById(id);
        return "Network engineer deleted successfully";
    }

    // NetworkEngineerService — C1
    public List<NetworkEngineer> findByCertification(String name) {
        return networkEngineerRepository.findByCertificationName(name);
    }
}
