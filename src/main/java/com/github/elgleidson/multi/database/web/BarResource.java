package com.github.elgleidson.multi.database.web;

import com.github.elgleidson.multi.database.service.BarService;
import com.github.elgleidson.multi.database.domain.db2.Bar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/bar")
public class BarResource {

    private static final Logger log = LoggerFactory.getLogger(BarResource.class);

    @Autowired
    private BarService service;

    @PostMapping
    public ResponseEntity<Bar> save(@RequestBody Bar bar) {
    	log.debug("HTTP Post {}", bar);
    	if (bar.getId() != null) {
    		return ResponseEntity.badRequest().body(bar);
    	}
        bar = service.save(bar);
        return ResponseEntity.ok(bar);
    }
    
    @PutMapping
    public ResponseEntity<Bar> update(@RequestBody Bar bar) {
    	log.debug("HTTP Put {}", bar);
    	if (bar.getId() == null) {
    		return ResponseEntity.badRequest().body(bar);
    	}
        bar = service.save(bar);
        return ResponseEntity.ok(bar);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody Bar bar) {
    	log.debug("HTTP Delete {}", bar);
        service.delete(bar);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bar> findById(@PathVariable Long id) {
    	log.debug("HTTP Get id={}", id);
        Optional<Bar> bar = service.findById(id);
        return bar.map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public Page<Bar> findAll(Pageable pageable) {
    	log.debug("HTTP Get all with page={}", pageable);
        Page<Bar> page = service.findAll(pageable);
        return page;
    }

}
