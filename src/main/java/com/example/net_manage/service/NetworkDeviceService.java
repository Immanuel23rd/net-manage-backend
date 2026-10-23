package com.example.net_manage.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.net_manage.domain.NetworkDevice;
import com.example.net_manage.repository.NetworkDeviceRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.UUID;


@Service
public class NetworkDeviceService {
    @Autowired
    private NetworkDeviceRepository networkDeviceRepository;

    @Transactional
    public NetworkDevice save(NetworkDevice networkDevice) {
        return networkDeviceRepository.save(networkDevice);
    }

    @Transactional
    public void delete(UUID id) {
        networkDeviceRepository.deleteById(id);
    }

    public Optional<NetworkDevice> findById(UUID id) {
        return networkDeviceRepository.findById(id);
    }

    public List<NetworkDevice> findAll() {
        return networkDeviceRepository.findAll();
    }

    public Page<NetworkDevice> findAll(Pageable pageable) {
        return networkDeviceRepository.findAll(pageable);
    }

    public List<NetworkDevice> findAll(Sort sort) {
        return networkDeviceRepository.findAll(sort);
    }
}
