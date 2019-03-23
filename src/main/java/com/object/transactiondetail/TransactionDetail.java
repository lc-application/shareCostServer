package com.object.transactiondetail;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

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

    public TransactionDetail(){}

    public TransactionDetail(String from, String to, int value){
        this.from = from;
        this.to = to;
        this.value = value;
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
