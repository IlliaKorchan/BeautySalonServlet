package model.entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class ClientAppointmentDto {
    private Integer id;
    private String masterName;
    private LocalDate date;
    private LocalTime time;
    private String procedureName;
    private Long procedurePrice;

    public ClientAppointmentDto() {
    }

    public ClientAppointmentDto(Integer id, String masterName, LocalDate date, LocalTime time,
                                String procedureName, Long procedurePrice) {
        this.id = id;
        this.masterName = masterName;
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

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
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
