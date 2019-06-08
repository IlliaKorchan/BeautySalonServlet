package model.entities;

public class Procedure {
    private Integer id;
    private String name;
    private Long price;
    private Integer time;

    public Procedure() {
    }

    public Procedure(Integer id, String name, Long price, Integer time) {
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
