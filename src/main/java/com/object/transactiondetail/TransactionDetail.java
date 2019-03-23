package com.object.transactiondetail;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="transactiondetail")
public class TransactionDetail {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "from")
    private String from;

    @Column(name = "to")
    private String to;

    @Column(name = "value")
    private int value;

    @Column(name = "title")
    private String title;

    @Column(name = "detail")
    private String detail;

    @Column(name = "date")
    private String date;

    public TransactionDetail(){}

    public TransactionDetail(String from, String to, int value, String title, String detail){
        this.from = from;
        this.to = to;
        this.value = value;
        this.title = title;
        this.detail = detail;
        Date date = new Date();
        this.date = date.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionDetail that = (TransactionDetail) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
