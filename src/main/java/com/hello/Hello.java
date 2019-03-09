package com.hello;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="hello")
public class Hello {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "massage")
    private String message;
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    /*
     * Read function
     */
    public String getMessage() {
        return message;
    }

    /*
     * Update function
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /*
     * Create function within an initialized value
     */
    public Hello(String message) {
        this.message = message;
    }

    /*
     * Create function
     */
    public Hello() {
    }

}