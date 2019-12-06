package com.qstpss.mockserver.model.entities;

import com.qstpss.mockserver.model.Status;
import com.qstpss.mockserver.model.Type;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Table(name = "MOCK_EVENT")
public class MockEvent {

    private MockEvent(){
    }

    public MockEvent(@NotNull Type type) {
        this.type = type;
        this.status = Status.PENDING;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private Type type;

    @NotNull
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "REGISTER_TIMESTAMP")
    @CreationTimestamp
    private Date registerTimestamp;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_TIMESTAMP")
    private Date startTimestamp;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_TIMESTAMP")
    private Date endTimestamp;

    public Long getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getRegisterTimestamp() {
        return registerTimestamp;
    }

    public Date getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Date startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public Date getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(Date endTimestamp) {
        this.endTimestamp = endTimestamp;
    }
}
