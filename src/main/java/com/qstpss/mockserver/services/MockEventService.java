package com.qstpss.mockserver.services;

import com.qstpss.mockserver.model.Type;
import com.qstpss.mockserver.model.entities.MockEvent;
import com.qstpss.mockserver.exceptions.NotUniqueEventException;

import java.util.List;

public interface MockEventService {
    MockEvent save(MockEvent mockEvent) throws NotUniqueEventException;

    MockEvent getById(Long id);

    List<MockEvent> getAll();

    MockEvent update(MockEvent mockEvent);

    MockEvent getActiveEvent(Type type);

    List<MockEvent> getAllActiveEvents();
}
