package model.entities;

public class Procedure {
    private Integer id;
    private String nameUkr;
    private String nameEn;
    private Long price;
    private Integer time;

    public Procedure() {
    }

    public Procedure(Integer id, String nameUkr, String nameEn, Long price, Integer time) {
        this.id = id;
        this.nameUkr = nameUkr;
        this.nameEn = nameEn;
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

    public String getNameUkr() {
        return nameUkr;
    }

    public void setNameUkr(String nameUkr) {
        this.nameUkr = nameUkr;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameEn() {
        return nameEn;
    }
}
