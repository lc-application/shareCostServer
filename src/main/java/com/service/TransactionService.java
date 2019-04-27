package com.service;

import com.object.transaction.Transaction;
import com.object.transaction.TransactionRepository;
import com.object.transactiondetail.TransactionDetail;
import com.object.transactiondetail.TransactionDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionDetailRepository transactionDetailRepository;
    @Autowired
    public TransactionService(TransactionRepository transactionRepository,
                              TransactionDetailRepository transactionDetailRepository) {
        this.transactionRepository = transactionRepository;
        this.transactionDetailRepository = transactionDetailRepository;
    }

    public List<Transaction> getAllTransactionByUserId(String userId){
        List<Transaction> fromList = transactionRepository.findAllByFrom(userId);
        List<Transaction> toList = transactionRepository.findAllByTo(userId);
        fromList.addAll(toList);
        return fromList;
    }

    public double getTransactionTotal(String userId){
        List<Transaction> transactionList = getAllTransactionByUserId(userId);
        double runningTotal = 0;
        for(Transaction transaction : transactionList){
            runningTotal += transaction.getValue();
        }
        return runningTotal;
    }


    public void createTransaction(String from, String to, double value, String title, String detail){
        if (transactionRepository.existsByFromAndTo(from,to)){
            Transaction transaction = transactionRepository.findByFromAndTo(from, to);
            transaction.addValue(value);
            transactionRepository.save(transaction);
        } else if (transactionRepository.existsByFromAndTo(to, from)) {
            Transaction transaction = transactionRepository.findByFromAndTo(to, from);
            transaction.addValue(value);
            transactionRepository.save(transaction);
        } else {
            Transaction transaction = new Transaction(from, to, value);
            transactionRepository.save(transaction);
            transactionRepository.flush();
        }

        transactionDetailRepository.save(new TransactionDetail(from, to, value, title, detail));
        transactionDetailRepository.flush();

    }

}

