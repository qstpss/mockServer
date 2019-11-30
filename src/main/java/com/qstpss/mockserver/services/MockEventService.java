package com.qstpss.mockserver.services;

import com.qstpss.mockserver.entities.MockEvent;

import java.util.List;

public interface MockEventService {
    MockEvent save(MockEvent mockEvent);
    MockEvent getById(Long id);
    List<MockEvent> getAll();
}
