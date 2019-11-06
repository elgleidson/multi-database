package com.github.elgleidson.multi.database.repository.db2;

import com.github.elgleidson.multi.database.domain.db2.Bar;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BarRepository extends PagingAndSortingRepository<Bar, Long> {
}
