package com.qstpss.mockserver.rest;

import com.qstpss.mockserver.exceptions.EventAlreadyExistException;
import com.qstpss.mockserver.exceptions.NotUniqueEventException;
import com.qstpss.mockserver.model.entities.MockEvent;
import com.qstpss.mockserver.services.MockEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/new")
@ResponseStatus(HttpStatus.CREATED)
public class CreateMockEventController {

    @Autowired
    private MockEventService mockEventService;

    @PostMapping("/vibration")
    private void createVibrationMockEvent(@RequestBody MockEvent vibrationMockEvent) {
        try {
            mockEventService.save(vibrationMockEvent);
        } catch (NotUniqueEventException e) {
            //TODO: log it
            throw new EventAlreadyExistException();
        }
    }

    @PostMapping("/mutemedia")
    private void createMuteMediaMockEvent(@RequestBody MockEvent muteMediaMockEvent) {
        try {
            mockEventService.save(muteMediaMockEvent);
        } catch (NotUniqueEventException e) {
            //TODO: log it
            throw new EventAlreadyExistException();
        }
    }

    @PostMapping("/mutealarm")
    private void createMuteAlarmMockEvent(@RequestBody MockEvent muteAlarmMockEvent) {
        try {
            mockEventService.save(muteAlarmMockEvent);
        } catch (NotUniqueEventException e) {
            //TODO: log it
            throw new EventAlreadyExistException();
        }
    }
}
