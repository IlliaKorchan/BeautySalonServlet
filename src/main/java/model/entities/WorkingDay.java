package model.entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class WorkingDay {
    private final LocalTime workingDayStart = LocalTime.of(9, 0);
    private final LocalTime workingDayEnd = LocalTime.of(19, 0);
    private Integer id;
    private Integer masterId;
    private LocalDate date;

    public WorkingDay(Integer id, Integer masterId, LocalDate date) {
        this.id = id;
        this.masterId = masterId;
        this.date = date;
    }

    public LocalTime getWorkingDayStart() {
        return workingDayStart;
    }

    public LocalTime getWorkingDayEnd() {
        return workingDayEnd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
