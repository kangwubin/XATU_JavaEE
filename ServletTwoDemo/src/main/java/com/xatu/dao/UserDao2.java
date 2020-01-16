package com.xatu.dao;

import com.xatu.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2020/1/15
 * @Time: 15:08
 */
public class UserDao2 {
    private static List<User> userList = new ArrayList<>();

    static {
        userList.add(new User(1, "KWB", "皇上"));
        userList.add(new User(1, "SY", "内阁首辅"));
    }

    public User findUserByUsernameAndPassword(String username, String password) {
        for (User user : userList) {
            if (user.username.equals(username)) {
                return user;
            }
        }
        return null;
    }
}
