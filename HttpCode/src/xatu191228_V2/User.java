package xatu191228_V2;

import java.io.Serializable;
import java.util.UUID;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/28
 * @Time: 16:32
 */
public class User implements Serializable {
    String id;
    String username;
    String avatarUrl;
    String show;

    User(String username, String avatarUrl, String show) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.avatarUrl = avatarUrl;
        this.show = show;
    }
}
