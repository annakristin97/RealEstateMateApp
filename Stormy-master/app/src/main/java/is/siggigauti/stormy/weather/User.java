package is.siggigauti.stormy.weather;


import java.util.ArrayList;
import java.util.List;

public class User {
    public long id;

    private static String userEmail;

    public static String userName;

    private static String userPassword;

    //@OneToMany(mappedBy = "users")

    public User() {
    }

    public User(String userName, String userPassword, String userEmail) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }

    public static String getUserEmail() {
        return userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public static String getUserPassword() {
        return userPassword;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public long getId() { return id; }

    public void setId(Long id) { this.id = id; }

}


