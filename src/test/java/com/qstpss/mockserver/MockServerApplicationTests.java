package com.qstpss.mockserver;

import com.qstpss.mockserver.entities.MockEvent;
import com.qstpss.mockserver.services.MockEventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MockServerApplicationTests {

    @Autowired
    MockEventService mockEventService;

    @Test
    void createMockEntity() {
        MockEvent newMockEvent = new MockEvent(Type.VIBRATION);
        mockEventService.save(newMockEvent);
        MockEvent loadedMockEvent = mockEventService.getById(newMockEvent.getId());
        assertNotNull(loadedMockEvent);
        assertNotNull(loadedMockEvent.getRegisterTimestamp());
        assertEquals(Status.PENDING, loadedMockEvent.getStatus());
    }

}
