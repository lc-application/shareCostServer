package com.object.friend;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="friend")
public class Friend {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "start")
    private String from;

    @Column(name = "receive")
    private String to;

    @Column(name = "date")
    private String date;

    @Column(name = "status")
    private int status;  // 0-pending 1-friend

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Friend(){}

    public Friend(String from, String to){
        this.from = from;
        this.to = to;
        this.status = 0;
        Date date = new Date();
        this.date = date.toString();
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Friend that = (Friend) o;
        return from.equals(that.from) &&
                to.equals(that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
