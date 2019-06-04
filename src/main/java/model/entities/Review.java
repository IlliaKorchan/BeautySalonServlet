package model.entities;

public class Review {
    private Integer id;
    private Integer clientId;
    private Integer appointmentId;
    private String text;

    public Review() {
    }

    public Review(Integer id, Integer clientId, Integer appointmentId, String text) {
        this.id = id;
        this.clientId = clientId;
        this.appointmentId = appointmentId;
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

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
