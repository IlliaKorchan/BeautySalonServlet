package model.entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private Integer id;
    private Integer userId;
    private Integer masterId;
    private LocalDate date;
    private LocalTime time;
    private Integer procedureId;

    public Appointment() {
    }

    public Appointment(Integer id, Integer userId, Integer masterId, LocalDate date, LocalTime time, Integer procedureId) {
        this.id = id;
        this.userId = userId;
        this.masterId = masterId;
        this.date = date;
        this.time = time;
        this.procedureId = procedureId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Integer getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(Integer procedureId) {
        this.procedureId = procedureId;
    }
}
