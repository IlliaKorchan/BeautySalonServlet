package model.entities;

public class UserDto {
    private User user;
    private String name;

    public UserDto(User user, String name) {
        this.user = user;
        this.name = name;
    }

    public UserDto() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
