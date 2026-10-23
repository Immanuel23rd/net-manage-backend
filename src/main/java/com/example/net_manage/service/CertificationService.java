package com.example.net_manage.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.net_manage.domain.Certification;
import com.example.net_manage.repository.CertificationRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.UUID;

@Service
public class CertificationService {
    @Autowired
    private CertificationRepository certificationRepository;

    @Transactional
    public Certification save(Certification certification) {
        return certificationRepository.save(certification);
    }

    @Transactional
    public void delete(UUID id) {
        certificationRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Certification> findById(UUID id) {
        return certificationRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Certification> findAll() {
        return certificationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Certification> findAll(Pageable pageable) {
        return certificationRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<Certification> findAll(Sort sort) {
        return certificationRepository.findAll(sort);
    }

    @Transactional(readOnly = true)
    public List<Certification> findByNetworkEngineerId(UUID networkEngineerId) {
        return certificationRepository.findByNetworkEngineerId(networkEngineerId);
    }

    @Transactional(readOnly = true)
    public List<Certification> findByNetworkEngineerIdAndCertificationName(UUID networkEngineerId, String certificationName) {
        return certificationRepository.findByNetworkEngineerIdAndCertificationName(networkEngineerId, certificationName);
    }

}
