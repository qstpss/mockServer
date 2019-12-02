package com.qstpss.mockserver.jparepositories;

import com.qstpss.mockserver.model.Type;
import com.qstpss.mockserver.model.entities.MockEvent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MockEventRepository extends CrudRepository<MockEvent, Long> {
    @Query("SELECT e FROM MockEvent e WHERE e.type = :type AND e.status IN ('PENDING', 'IN_PROGRESS')")
    MockEvent getActiveEventByType(@Param("type") Type type);
}
