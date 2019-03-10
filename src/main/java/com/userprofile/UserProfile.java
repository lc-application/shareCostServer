package com.userprofile;// Created by xuanyuli on 3/9/19.

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

  UserProfile(String username, String firstName, String lastName,
              String email, String phoneNumber, boolean showName,
              boolean showEmail, boolean showPhoneNumber) {
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.showName = showName;
    this.showEmail = showEmail;
    this.showPhoneNumber = showPhoneNumber;
  }

  UserProfile(){}

  public void setUsername(String username) {
    this.username = username;
  }

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

  public boolean isShowName() {
    return showName;
  }

  public boolean isShowEmail() {
    return showEmail;
  }

  public boolean isShowPhoneNumber() {
    return showPhoneNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserProfile that = (UserProfile) o;
    return id == that.id &&
            isShowName() == that.isShowName() &&
            isShowEmail() == that.isShowEmail() &&
            isShowPhoneNumber() == that.isShowPhoneNumber() &&
            Objects.equals(getUsername(), that.getUsername()) &&
            Objects.equals(getFirstName(), that.getFirstName()) &&
            Objects.equals(getLastName(), that.getLastName()) &&
            Objects.equals(getEmail(), that.getEmail()) &&
            Objects.equals(getPhoneNumber(), that.getPhoneNumber());
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, getUsername(), getFirstName(), getLastName(),
                            getEmail(), getPhoneNumber(), isShowName(),
                            isShowEmail(), isShowPhoneNumber());
  }
}
