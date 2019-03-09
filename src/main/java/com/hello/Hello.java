package com.hello;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="hello")
public class Hello {
    @Id
    @GeneratedValue
    private int id;
    public int getId() {
        return id;
    }
    private String message;

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