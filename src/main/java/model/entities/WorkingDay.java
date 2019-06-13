package model.entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class WorkingDay {
    private Integer id;
    private Integer masterId;
    private LocalDate date;

    public WorkingDay(Integer id, Integer masterId, LocalDate date) {
        this.id = id;
        this.masterId = masterId;
        this.date = date;
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
