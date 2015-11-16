package model;

/**
 * Created by Iris on 14/11/2015.
 */
public class User {
    private int userId;
    private String name;
    private String email;
    private String password;
    private String location;

    public User(int userId, String name, String email, String password, String location) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.location = location;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    
}