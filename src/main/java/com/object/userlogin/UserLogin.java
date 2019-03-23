package com.object.userlogin;

import java.util.Objects;

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


    public UserLogin(String username, String password){
        this.username = username;

        this.password = password;
    }

    public UserLogin(){

    }
    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLogin userLogin = (UserLogin) o;
        return Objects.equals(getUsername(), userLogin.getUsername());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUsername());
    }

    public void setUsername(String username) {
        this.username = username;

    }

    public void setPassword(String password) {
        this.password = password;
    }

}
