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
    private NetworkDeviceRepository NetworkDeviceRepository;

    public NetworkDeviceService(NetworkDeviceRepository NetworkDeviceRepository) {
        this.NetworkDeviceRepository = NetworkDeviceRepository;
    }

    public String save(NetworkDevice c) {
        NetworkDeviceRepository.save(c);
        return "Network device saved successfully";
    }

    public List<NetworkDevice> findAll() { return NetworkDeviceRepository.findAll(); }

    public Optional<NetworkDevice> findById(UUID id) { return NetworkDeviceRepository.findById(id); }

    public String update(UUID id, NetworkDevice incoming) {
        Optional<NetworkDevice> found = NetworkDeviceRepository.findById(id);
        if (found.isEmpty()) return "The network device with that id does not exist";
        NetworkDevice existing = found.get();      
        existing.setHostname(incoming.getHostname());   
        existing.setIpAddress(incoming.getIpAddress());
        existing.setModel(incoming.getModel());
        existing.setSerialNumber(incoming.getSerialNumber());      
        NetworkDeviceRepository.save(existing);
        return "Network device updated successfully";
    }

    public String delete(UUID id) {
        if (!NetworkDeviceRepository.existsById(id)) return "The network device with that id does not exist";
        NetworkDeviceRepository.deleteById(id);
        return "Network device deleted successfully";
    }
}
