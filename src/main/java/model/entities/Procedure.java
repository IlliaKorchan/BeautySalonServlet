package model.entities;

public class Procedure {
    private Integer id;
    private Long price;
    private Integer time;

    public Procedure() {
    }

    public Procedure(Integer id, Long price, Integer time) {
        this.id = id;
        this.price = price;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
