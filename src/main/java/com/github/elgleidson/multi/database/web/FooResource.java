package com.github.elgleidson.multi.database.web;

import com.github.elgleidson.multi.database.domain.db1.Foo;
import com.github.elgleidson.multi.database.service.FooService;
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
@RequestMapping("/api/foo")
public class FooResource {

    private static final Logger log = LoggerFactory.getLogger(FooResource.class);

    @Autowired
    private FooService service;

    @PostMapping
    public ResponseEntity<Foo> save(@RequestBody Foo foo) {
    	log.debug("HTTP Post {}", foo);
    	if (foo.getId() != null) {
    		return ResponseEntity.badRequest().body(foo);
    	}
        foo = service.save(foo);
        return ResponseEntity.ok(foo);
    }
    
    @PutMapping
    public ResponseEntity<Foo> update(@RequestBody Foo foo) {
    	log.debug("HTTP Put {}", foo);
    	if (foo.getId() == null) {
    		return ResponseEntity.badRequest().body(foo);
    	}
        foo = service.save(foo);
        return ResponseEntity.ok(foo);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody Foo foo) {
    	log.debug("HTTP Delete {}", foo);
        service.delete(foo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Foo> findById(@PathVariable Long id) {
    	log.debug("HTTP Get id={}", id);
        Optional<Foo> foo = service.findById(id);
        return foo.map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public Page<Foo> findAll(Pageable pageable) {
    	log.debug("HTTP Get all with page={}", pageable);
        Page<Foo> page = service.findAll(pageable);
        return page;
    }

}
