package com.qstpss.mockserver.rest.controllers;

import com.qstpss.mockserver.exceptions.EventAlreadyExistException;
import com.qstpss.mockserver.exceptions.NotUniqueEventException;
import com.qstpss.mockserver.model.entities.MockEvent;
import com.qstpss.mockserver.services.MockEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/edit")
    private MockEvent editMockEvent(@RequestBody MockEvent mockEvent) {
        return mockEventService.update(mockEvent);
    }


}

