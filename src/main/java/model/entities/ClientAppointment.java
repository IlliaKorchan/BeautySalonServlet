package model.entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class ClientAppointment {
    private Integer id;
    private String surname;
    private LocalDate date;
    private LocalTime time;
    private String procedureName;
    private Long procedurePrice;

    public ClientAppointment() {
    }

    public ClientAppointment(Integer id, String surname, LocalDate date, LocalTime time,
                             String procedureName, Long procedurePrice) {
        this.id = id;
        this.surname = surname;
        this.date = date;
        this.time = time;
        this.procedureName = procedureName;
        this.procedurePrice = procedurePrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer appointmentId) {
        this.id = appointmentId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public Long getProcedurePrice() {
        return procedurePrice;
    }

    public void setProcedurePrice(Long procedurePrice) {
        this.procedurePrice = procedurePrice;
    }
}
