package com.example.net_manage.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.net_manage.domain.NetworkEngineer;
import com.example.net_manage.repository.NetworkEngineerRepository;

@Service
public class NetworkEngineerService {
    @Autowired
    private NetworkEngineerRepository networkEngineerRepository;

    @Transactional
    public void delete(UUID id) {
        networkEngineerRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<NetworkEngineer> findAll() {
        return networkEngineerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<NetworkEngineer> findAll(Pageable pageable) {
        return networkEngineerRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<NetworkEngineer> findAll(Sort sort) {
        return networkEngineerRepository.findAll(sort);
    }

    @Transactional(readOnly = true)
    public Optional<NetworkEngineer> findById(UUID id) {
        return networkEngineerRepository.findById(id);
    }

    public String getNetworkEngineerFirstNameById(UUID id) {
        Optional<NetworkEngineer> networkEngineerOptional = networkEngineerRepository.findById(id);
        return networkEngineerOptional.isPresent()
            ? networkEngineerOptional.get().getFirstName()
            : null;
    }

    @Transactional
    public NetworkEngineer save(NetworkEngineer networkEngineer) {
        return networkEngineerRepository.save(networkEngineer);
    }
}
