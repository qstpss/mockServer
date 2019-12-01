package com.qstpss.mockserver.services;

import com.qstpss.mockserver.model.entities.Log;

import java.util.List;

public interface LogService {
    void write(Log log);
    void writeError(String messsage);
    List<Log> getAll();
}
