package com.qstpss.mockserver.rest.controllers;

import com.qstpss.mockserver.exceptions.EventAlreadyExistException;
import com.qstpss.mockserver.exceptions.NotUniqueEventException;
import com.qstpss.mockserver.model.Status;
import com.qstpss.mockserver.model.Type;
import com.qstpss.mockserver.model.entities.MockEvent;
import com.qstpss.mockserver.services.MockEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rest")

public class MockEventController {

    @Autowired
    private MockEventService mockEventService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/new")
    private MockEvent createMockEvent(@RequestBody MockEvent mockEvent) {
        try {
            return mockEventService.save(mockEvent);
        } catch (NotUniqueEventException e) {
            throw new EventAlreadyExistException();
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/start")
    private MockEvent startMockEvent(@RequestBody MockEvent mockEvent) {
        mockEvent.setStatus(Status.IN_PROGRESS);
        mockEvent.setStartTimestamp(new Date());
        return mockEventService.update(mockEvent);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/end")
    private MockEvent endMockEvent(@RequestBody MockEvent mockEvent) {
        return finishMockEvent(mockEvent);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/startedEvents")
    private List<MockEvent> getAllActiveEvents() {
        return mockEventService.getAllActiveEvents();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/finishAllEvents")
    private void finishAllActiveEvents() {
        List<MockEvent> allActiveEvents = mockEventService.getAllActiveEvents();
        allActiveEvents.forEach(this::finishMockEvent);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/actualEventByType={type}")
    private MockEvent getActiveEventByType(@PathVariable String type) {
        Type eventType = Type.valueOf(type);
        return mockEventService.getActiveEvent(eventType);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    private List<MockEvent> getAllMockEvents() {
        return mockEventService.getAll();
    }

    private MockEvent finishMockEvent(MockEvent mockEvent) {
        mockEvent.setStatus(Status.CANCELED);
        mockEvent.setEndTimestamp(new Date());
        return mockEventService.update(mockEvent);
    }

}

