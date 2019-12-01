package com.qstpss.mockserver.jparepositories;

import com.qstpss.mockserver.Type;
import com.qstpss.mockserver.entities.MockEvent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MockEventRepository extends CrudRepository<MockEvent, Long> {
    @Query("SELECT e FROM MockEvent e WHERE e.type = :type AND e.status IN ('PENDING', 'IN_PROGRESS')")
    List<MockEvent> getAllActiveEqualEventsByType(@Param("type") Type type);
}
