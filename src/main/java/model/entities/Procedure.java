package model.entities;

public class Procedure {
    private Integer id;
    private String nameUkr;
    private String nameEn;
    private Long price;

    public Procedure() {
    }

    public Procedure(Integer id, String nameUkr, String nameEn, Long price) {
        this.id = id;
        this.nameUkr = nameUkr;
        this.nameEn = nameEn;
        this.price = price;
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
