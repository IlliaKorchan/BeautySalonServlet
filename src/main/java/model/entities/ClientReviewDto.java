package model.entities;

import java.time.LocalDate;

public class ClientReviewDto {
    private Integer id;
    private LocalDate date;
    private String procedureName;
    private String masterSurname;
    private String text;

    public ClientReviewDto(Integer id, LocalDate date, String procedureName, String masterSurname, String text) {
        this.id = id;
        this.date = date;
        this.procedureName = procedureName;
        this.masterSurname = masterSurname;
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public String getMasterSurname() {
        return masterSurname;
    }

    public void setMasterSurname(String masterSurname) {
        this.masterSurname = masterSurname;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
