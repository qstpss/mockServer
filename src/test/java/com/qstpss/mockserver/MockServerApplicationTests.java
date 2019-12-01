package com.qstpss.mockserver;

import com.qstpss.mockserver.model.SeverityLevel;
import com.qstpss.mockserver.model.Status;
import com.qstpss.mockserver.model.Type;
import com.qstpss.mockserver.model.entities.Log;
import com.qstpss.mockserver.model.entities.MockEvent;
import com.qstpss.mockserver.exceptions.NotUniqueEventException;
import com.qstpss.mockserver.services.LogService;
import com.qstpss.mockserver.services.MockEventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MockServerApplicationTests {

    @Autowired
    private MockEventService mockEventService;

    @Autowired
    private LogService logService;

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
    void testUniqueEventAndLogs() throws NotUniqueEventException {
        MockEvent muteEvent1 = new MockEvent(Type.MUTE_MEDIA);
        mockEventService.save(muteEvent1);
        MockEvent muteEvent2 = new MockEvent(Type.MUTE_MEDIA);
        assertThrows(NotUniqueEventException.class,
                () -> mockEventService.save(muteEvent2));
        List<Log> logList = logService.getAll();
        assertEquals(1, logList.size());
        Log errorLog = logList.get(0);
        assertEquals("The same event is already exists!", errorLog.getMessage());
        assertEquals(SeverityLevel.ERROR, errorLog.getSeverity());
    }

}
