package com.qstpss.mockserver.services;

import com.qstpss.mockserver.jparepositories.LogRepository;
import com.qstpss.mockserver.model.SeverityLevel;
import com.qstpss.mockserver.model.entities.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Transactional
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository logRepository;

    @Override
    public void write(Log log) {
        logRepository.save(log);
    }

    @Override
    public void writeError(String message) {
        Log log = new Log(SeverityLevel.ERROR, message);
        logRepository.save(log);
    }

    @Override
    public List<Log> getAll() {
        return new ArrayList<>(
                (Collection<? extends Log>) logRepository.findAll());
    }
}
