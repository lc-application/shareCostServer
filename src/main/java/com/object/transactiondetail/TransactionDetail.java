package com.object.transactiondetail;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transactiondetail")
public class TransactionDetail {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "start")
    private String from;

    @Column(name = "receive")
    private String to;

    @Column(name = "value")
    private double value;

    @Column(name = "title")
    private String title;

    @Column(name = "detail")
    private String detail;

    @Column(name = "date")
    private String date;

    public TransactionDetail(){}

    public TransactionDetail(String from, String to, double value, String title, String detail){
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
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
