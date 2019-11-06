package com.github.elgleidson.multi.database.service;

import com.github.elgleidson.multi.database.domain.db2.Bar;
import com.github.elgleidson.multi.database.repository.db2.BarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BarService {

    private static final Logger log = LoggerFactory.getLogger(BarService.class);

    @Autowired
    private BarRepository repository;

    @Transactional(transactionManager = "barTransactionManager")
    public Bar save(Bar bar) {
        log.debug("Saving Bar {}...", bar);
        bar = repository.save(bar);
        log.debug("Bar saved {}!", bar);
        return bar;
    }

    @Transactional(transactionManager = "barTransactionManager")
    public void delete(Bar bar) {
        log.debug("Deleting Bar {}...", bar);
        repository.delete(bar);
        log.debug("Deleted Bar {}", bar);
    }

    public Optional<Bar> findById(Long id) {
        log.debug("Finding Bar by id {}...", id);
        Optional<Bar> bar = repository.findById(id);
        log.debug("Found Bar {}!", bar);
        return bar;
    }

    public Page<Bar> findAll(Pageable pageable) {
        log.debug("Finding all Bar with page {}...", pageable);
        Page<Bar> page = repository.findAll(pageable);
        log.debug("Found all Bar with page {}!", page);
        return page;
    }

}
