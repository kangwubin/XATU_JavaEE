package com.xatu.model;

import java.io.Serializable;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2020/1/16
 * @Time: 10:18
 */
//实现Serializable---为持久化作准备
public class User implements Serializable {
    private int id;
    private String username;
    private String nickname;

    public User(int id, String username, String nickname) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}

