package com.qstpss.mockserver.model.entities;

import com.qstpss.mockserver.model.SeverityLevel;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "LOG")
public class Log {

    public Log(@NotNull SeverityLevel severity, String message) {
        this.severity = severity;
        this.message = message;
    }

    private Log() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "SEVERITY_LEVEL")
    @Enumerated(EnumType.STRING)
    private SeverityLevel severity;

    @Column(name = "TIMESTAMP")
    @CreationTimestamp
    private Date timestamp;

    @Column(name = "MESSAGE")
    private String message;

    public Long getId() {
        return id;
    }

    public SeverityLevel getSeverity() {
        return severity;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }
}
