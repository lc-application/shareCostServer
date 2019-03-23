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

    public List<Transaction> getAllTransactionByUserName(String username){
        return transactionRepository.findAllByFrom(username);
    }

    public int getTransactionTotal(String username){
        List<Transaction> transactionList = getAllTransactionByUserName(username);
        int runningTotal = 0;
        for(Transaction transaction : transactionList){
            runningTotal += transaction.getValue();
        }
        return runningTotal;
    }


    public void createTransaction(String from, String to, int value, String title, String detail){
        if (transactionRepository.existsByFromAndTo(from,to)){
            Transaction transaction = transactionRepository.findByFromAndTo(from, to);
            transaction.setValue(transaction.getValue() + value);
            transactionRepository.save(transaction);
        } else {
            Transaction transaction = new Transaction(from, to);
            transaction.setValue(value);
            transactionRepository.save(transaction);
            transactionRepository.flush();
        }

        transactionDetailRepository.save(new TransactionDetail(from, to, value, title, detail));
        transactionDetailRepository.flush();

    }

}

