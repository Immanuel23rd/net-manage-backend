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
    private NetworkEngineerRepository NetworkEngineerRepository;

    public NetworkEngineerService(NetworkEngineerRepository NetworkEngineerRepository) {
        this.NetworkEngineerRepository = NetworkEngineerRepository;
    }

    public String save(NetworkEngineer c) {
        NetworkEngineerRepository.save(c);
        return "Network engineer saved successfully";
    }

    public List<NetworkEngineer> findAll() { return NetworkEngineerRepository.findAll(); }

    public Optional<NetworkEngineer> findById(UUID id) { return NetworkEngineerRepository.findById(id); }

    public String update(UUID id, NetworkEngineer incoming) {
        Optional<NetworkEngineer> found = NetworkEngineerRepository.findById(id);
        if (found.isEmpty()) return "The network engineer with that id does not exist";
        NetworkEngineer existing = found.get();      
        existing.setFirstName(incoming.getFirstName());  
        existing.setLastName(incoming.getLastName());
        existing.setEmail(incoming.getEmail());
        existing.setSite(incoming.getSite());       
        NetworkEngineerRepository.save(existing);
        return "Network engineer updated successfully";
    }

    public String delete(UUID id) {
        if (!NetworkEngineerRepository.existsById(id)) return "The network engineer with that id does not exist";
        NetworkEngineerRepository.deleteById(id);
        return "Network engineer deleted successfully";
    }
}
