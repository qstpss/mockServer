package com.qstpss.mockserver.jparepositories;

import com.qstpss.mockserver.entities.MockEvent;
import org.springframework.data.repository.CrudRepository;

public interface MockEventRepository extends CrudRepository<MockEvent, Long> {
}
