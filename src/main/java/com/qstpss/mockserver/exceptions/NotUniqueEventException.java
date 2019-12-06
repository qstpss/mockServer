package com.qstpss.mockserver.exceptions;

public class NotUniqueEventException extends Exception {
    public NotUniqueEventException() {
        super("The same event is already exists!");
    }
}
