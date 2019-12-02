package com.qstpss.mockserver.rest.controllers;

import com.qstpss.mockserver.exceptions.EventAlreadyExistException;
import com.qstpss.mockserver.exceptions.NotUniqueEventException;
import com.qstpss.mockserver.model.Status;
import com.qstpss.mockserver.model.entities.MockEvent;
import com.qstpss.mockserver.services.MockEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
        mockEvent.setStatus(Status.CANCELED);
        mockEvent.setEndTimestamp(new Date());
        return mockEventService.update(mockEvent);
    }

    //todo get active all -> think about user experience


}

