package com.github.elgleidson.multi.database.service;

import com.github.elgleidson.multi.database.domain.db1.Foo;
import com.github.elgleidson.multi.database.repository.db1.FooRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FooService {

    private static final Logger log = LoggerFactory.getLogger(FooService.class);

    @Autowired
    private FooRepository repository;

    @Transactional(transactionManager = "fooTransactionManager")
    public Foo save(Foo foo) {
        log.debug("Saving Foo {}...", foo);
        foo = repository.save(foo);
        log.debug("Foo saved {}!", foo);
        return foo;
    }

    @Transactional(transactionManager = "fooTransactionManager")
    public void delete(Foo foo) {
        log.debug("Deleting Foo {}...", foo);
        repository.delete(foo);
        log.debug("Deleted Foo {}", foo);
    }

    public Optional<Foo> findById(Long id) {
        log.debug("Finding Foo by id {}...", id);
        Optional<Foo> foo = repository.findById(id);
        log.debug("Found Foo {}!", foo);
        return foo;
    }

    public Page<Foo> findAll(Pageable pageable) {
        log.debug("Finding all Foo with page {}...", pageable);
        Page<Foo> page = repository.findAll(pageable);
        log.debug("Found all Foo with page {}!", page);
        return page;
    }

}
