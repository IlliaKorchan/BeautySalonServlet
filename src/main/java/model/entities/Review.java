package model.entities;

import java.time.LocalDate;

public class Review {
    private Integer id;
    private LocalDate date;
    private Integer clientId;
    private Integer masterId;
    private String text;

    public Review() {
    }

    public Review(Integer id, LocalDate date, Integer clientId, Integer masterId, String text) {
        this.id = id;
        this.date = date;
        this.clientId = clientId;
        this.masterId = masterId;
        this.text = text;
    }

    public Review(LocalDate date, Integer clientId, Integer masterId, String text) {
        this.date = date;
        this.clientId = clientId;
        this.masterId = masterId;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
