package com.xatu.model;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2020/1/15
 * @Time: 10:11
 */
public class User {
    public int Id;
    public String username;
    public String nickUsername;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickUsername() {
        return nickUsername;
    }

    public void setNickUsername(String nickUsername) {
        this.nickUsername = nickUsername;
    }

    public User(int id, String username, String nickUsername) {
        Id = id;
        this.username = username;
        this.nickUsername = nickUsername;

    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", username='" + username + '\'' +
                ", nickUsername='" + nickUsername + '\'' +
                '}';
    }
}
