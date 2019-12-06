package com.qstpss.mockserver.services;

import com.qstpss.mockserver.model.Type;
import com.qstpss.mockserver.model.entities.MockEvent;
import com.qstpss.mockserver.exceptions.NotUniqueEventException;
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

    @Autowired
    private LogService logService;

    @Override
    public MockEvent save(MockEvent mockEvent) throws NotUniqueEventException {
        MockEvent activeEvent =
                mockEventRepository.getActiveEventByType(mockEvent.getType());
        if (activeEvent == null) {
            return mockEventRepository.save(mockEvent);
        } else {
            NotUniqueEventException notUniqueEventException = new NotUniqueEventException();
            logService.writeError(notUniqueEventException.getMessage());
            throw notUniqueEventException;
        }

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

    @Override
    public MockEvent update(MockEvent mockEvent) {
        return mockEventRepository.save(mockEvent);
    }

    @Override
    public MockEvent getActiveEvent(Type type) {
        return mockEventRepository.getActiveEventByType(type);
    }

    @Override
    public List<MockEvent> getAllActiveEvents() {
        return mockEventRepository.getAllActiveEvents();
    }
}
