package com.example.net_manage.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.net_manage.domain.NetworkDevice;
import com.example.net_manage.repository.NetworkDeviceRepository;



@Service
public class NetworkDeviceService {
    @Autowired 
    private NetworkDeviceRepository networkDeviceRepository;

    public NetworkDeviceService(NetworkDeviceRepository NetworkDeviceRepository) {
        this.networkDeviceRepository = NetworkDeviceRepository;
    }

    public String save(NetworkDevice c) {
        networkDeviceRepository.save(c);
        return "Network device saved successfully";
    }

    public List<NetworkDevice> findAll() { return networkDeviceRepository.findAll(); }

    public Optional<NetworkDevice> findById(UUID id) { return networkDeviceRepository.findById(id); }

    public String update(UUID id, NetworkDevice incoming) {
        Optional<NetworkDevice> found = networkDeviceRepository.findById(id);
        if (found.isEmpty()) return "The network device with that id does not exist";
        NetworkDevice existing = found.get();      
        existing.setHostname(incoming.getHostname());   
        existing.setIpAddress(incoming.getIpAddress());
        existing.setModel(incoming.getModel());
        existing.setSerialNumber(incoming.getSerialNumber());      
        networkDeviceRepository.save(existing);
        return "Network device updated successfully";
    }

    public String delete(UUID id) {
        if (!networkDeviceRepository.existsById(id)) return "The network device with that id does not exist";
        networkDeviceRepository.deleteById(id);
        return "Network device deleted successfully";
    }

    // B1
    public List<NetworkDevice> findByModel(String model) {
        return networkDeviceRepository.findByModelIgnoreCaseOrderByHostnameAsc(model);
    }

    // C4
    public List<NetworkDevice> findByEngineer(UUID engineerId) {
        return networkDeviceRepository.findByEngineer(engineerId);
    }

}
