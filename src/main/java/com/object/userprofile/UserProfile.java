package com.object.userprofile;// Created by xuanyuli on 3/9/19.

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userprofile")
public class UserProfile {
  @Id
  @GeneratedValue
  private int id;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "firstname")
  private String firstName;

  @Column(name = "lastname")
  private String lastName;

  @Column(name = "email")
  private String email;

  @Column(name = "phoneNumber")
  private String phoneNumber;

  @Column(name = "showname")
  private boolean showName;

  @Column(name = "showemail")
  private boolean showEmail;

  @Column(name = "showphonenumber")
  private boolean showPhoneNumber;

  public UserProfile(String username, String password, String firstName, String lastName,
              String email, String phoneNumber, boolean showName,
              boolean showEmail, boolean showPhoneNumber) {
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.showName = showName;
    this.showEmail = showEmail;
    this.showPhoneNumber = showPhoneNumber;
  }

  public UserProfile(){}

  public int getId(){
    return this.id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {this.password = password;}

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void setShowName(boolean showName) {
    this.showName = showName;
  }

  public void setShowEmail(boolean showEmail) {
    this.showEmail = showEmail;
  }

  public void setShowPhoneNumber(boolean showPhoneNumber) {
    this.showPhoneNumber = showPhoneNumber;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() { return password; }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public boolean getShowName() { return this.showName; }

  public boolean getShowEmail() {
    return showEmail;
  }

  public boolean getShowPhoneNumber() {
    return showPhoneNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserProfile that = (UserProfile) o;
    return id == that.id || username.equals(that.username);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
