package com.github.elgleidson.multi.database.repository.db1;

import com.github.elgleidson.multi.database.domain.db1.Foo;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FooRepository extends PagingAndSortingRepository<Foo, Long> {
}
