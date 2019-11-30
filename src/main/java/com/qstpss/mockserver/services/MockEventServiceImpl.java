package com.qstpss.mockserver.services;

import com.qstpss.mockserver.entities.MockEvent;
import com.qstpss.mockserver.jparepositories.MockEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Transactional
@Service
public class MockEventServiceImpl implements MockEventService {
    @Autowired
    private MockEventRepository mockEventRepository;

    @Override
    public MockEvent save(MockEvent mockEvent) {
        return mockEventRepository.save(mockEvent);
    }

    @Override
    public MockEvent getById(Long id) {
        return mockEventRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<MockEvent> getAll() {
        return new ArrayList<>(
                (Collection<? extends MockEvent>) mockEventRepository.findAll());
    }
}
