package com.qstpss.mockserver;

import com.qstpss.mockserver.entities.MockEvent;
import com.qstpss.mockserver.exceptions.NotUniqueEventException;
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
    void createMockEntity() throws NotUniqueEventException {
        MockEvent newMockEvent = new MockEvent(Type.VIBRATION);
        mockEventService.save(newMockEvent);
        MockEvent loadedMockEvent = mockEventService.getById(newMockEvent.getId());
        assertNotNull(loadedMockEvent);
        assertNotNull(loadedMockEvent.getRegisterTimestamp());
        assertEquals(Status.PENDING, loadedMockEvent.getStatus());
    }

    @Test
    void testUniqueEvent() throws NotUniqueEventException {
        MockEvent muteEvent1 = new MockEvent(Type.MUTE_MEDIA);
        mockEventService.save(muteEvent1);
        MockEvent muteEvent2 = new MockEvent(Type.MUTE_MEDIA);
        assertThrows(NotUniqueEventException.class,
                () -> mockEventService.save(muteEvent2));

    }

}
