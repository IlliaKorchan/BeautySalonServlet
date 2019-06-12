package model.entities;

import java.time.LocalDate;

public class ReviewDto {
    private Integer id;
    private LocalDate date;
    private String procedureName;
    private String name;
    private String text;

    public ReviewDto(Integer id, LocalDate date, String procedureName, String name, String text) {
        this.id = id;
        this.date = date;
        this.procedureName = procedureName;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
