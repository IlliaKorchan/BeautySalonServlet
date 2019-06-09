package model.entities;

public class User {
    private Integer id;
    private String nameUkr;
    private String nameEn;
    private String surnameUkr;
    private String surnameEn;
    private String login;
    private String password;
    private String gender;
    private String email;
    private String role;
    private Boolean active;
    private Long amountOfMoney;

    public User() {
    }

    public User(String nameUkr, String nameEn, String surnameUkr, String surnameEn, String login,
                String password, String gender, String email, String role, Long amountOfMoney) {
        this.nameUkr = nameUkr;
        this.nameEn = nameEn;
        this.surnameUkr = surnameUkr;
        this.surnameEn = surnameEn;
        this.login = login;
        this.password = password;
        this.gender = gender;
        this.email = email;
        this.role = role;
        this.amountOfMoney = amountOfMoney;
    }

    public User(Integer id, String nameUkr, String nameEn, String surnameUkr, String surnameEn, String login,
                String password, String gender, String email, String role, Boolean active, Long amountOfMoney) {
        this.id = id;
        this.nameUkr = nameUkr;
        this.nameEn = nameEn;
        this.surnameUkr = surnameUkr;
        this.surnameEn = surnameEn;
        this.login = login;
        this.password = password;
        this.gender = gender;
        this.email = email;
        this.role = role;
        this.active = active;
        this.amountOfMoney = amountOfMoney;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameUkr() {
        return nameUkr;
    }

    public void setNameUkr(String nameUkr) {
        this.nameUkr = nameUkr;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getSurnameUkr() {
        return surnameUkr;
    }

    public void setSurnameUkr(String surnameUkr) {
        this.surnameUkr = surnameUkr;
    }

    public String getSurnameEn() {
        return surnameEn;
    }

    public void setSurnameEn(String surnameEn) {
        this.surnameEn = surnameEn;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(Long amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }
}

