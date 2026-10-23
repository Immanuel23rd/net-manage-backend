package com.example.net_manage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.net_manage.domain.Site;
import com.example.net_manage.repository.SiteRepository;

@Service
public class SiteService {

    private final SiteRepository siteRepository;

    @Autowired
    public SiteService(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    @Transactional(readOnly = true)
    public List<Site> getAllSites() {
        return siteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Site> getSiteById(Long id) {
        return siteRepository.findById(id);
    }

    @Transactional
    public Site createSite(Site site) {
        return siteRepository.save(site);
    }

    @Transactional
    public Site updateSite(Site site) {
        return siteRepository.save(site);
    }

    @Transactional
    public void deleteSite(Long id) {
        siteRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<Site> getSites(Pageable pageable) {
        return siteRepository.findAll(pageable);
    }

    public Page<Site> getSites(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return siteRepository.findAll(pageable);
    }



}
