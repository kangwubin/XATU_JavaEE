import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    String username;
    int 账户余额;

    public User(String username, int 账户余额) {
        this.username = username;
        this.账户余额 = 账户余额;
    }

    static List<User> users = new ArrayList<>();
    static {
        users.add(new User("马云", 300));
        users.add(new User("马化腾", 350));
        users.add(new User("李彦宏", 150));
        users.add(new User("高博", 550));
    }

    public static User find(String username) {
        for (User user : users) {
            if (user.username.equals(username)) {
                return user;
            }
        }

        return null;
    }
}
