package com.qstpss.mockserver.jparepositories;

import com.qstpss.mockserver.model.entities.Log;
import org.springframework.data.repository.CrudRepository;

public interface LogRepository extends CrudRepository<Log, Long> {
}
