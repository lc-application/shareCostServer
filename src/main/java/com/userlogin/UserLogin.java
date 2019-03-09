package com.userlogin;

import javax.persistence.*;

@Entity
@Table(name="userlogin")
public class UserLogin {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    UserLogin(String username, String password){
        this.username = username;
        this.password = password;
    }
    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }
}
