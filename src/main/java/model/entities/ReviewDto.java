package model.entities;

import java.time.LocalDate;

public class ReviewDto {
    private Integer id;
    private LocalDate date;
    private String procedureName;
    private String surname;
    private String text;

    public ReviewDto(Integer id, LocalDate date, String procedureName, String surname, String text) {
        this.id = id;
        this.date = date;
        this.procedureName = procedureName;
        this.surname = surname;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
