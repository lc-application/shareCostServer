package com.object.transaction;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="transaction")
public class Transaction {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "from")
    private String from;

    @Column(name = "to")
    private String to;

    @Column(name = "value")
    private int value;

    public Transaction(){}

    public Transaction(String from, String to){
        this.from = from;
        this.to = to;
        this.value = 0;
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return from.equals(that.from) &&
                to.equals(that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
