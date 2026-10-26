package com.example.net_manage.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.net_manage.domain.Certification;
import com.example.net_manage.repository.CertificationRepository;

@Service
public class CertificationService {
    @Autowired 
    private CertificationRepository certificationRepository;

    public CertificationService(CertificationRepository certificationRepository) {
        this.certificationRepository = certificationRepository;
    }

    public String save(Certification c) {
        certificationRepository.save(c);
        return "Certification saved successfully";
    }

    public List<Certification> findAll() { return certificationRepository.findAll(); }

    public Optional<Certification> findById(UUID id) { return certificationRepository.findById(id); }

    public String update(UUID id, Certification incoming) {
        Optional<Certification> found = certificationRepository.findById(id);
        if (found.isEmpty()) return "The certification with that id does not exist";
        Certification existing = found.get();      
        existing.setName(incoming.getName());      
        certificationRepository.save(existing);
        return "Certification updated successfully";
    }

    public String delete(UUID id) {
        if (!certificationRepository.existsById(id)) return "The certification with that id does not exist";
        certificationRepository.deleteById(id);
        return "Certification deleted successfully";
    }
}
