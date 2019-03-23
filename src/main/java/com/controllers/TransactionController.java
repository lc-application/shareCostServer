package com.controllers;

import com.object.transaction.Transaction;
import com.service.TransactionService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/api/transaction/create")
    public ResponseEntity transactionCreate(@RequestBody JSONObject request) {
        if (request == null) {
            return ResponseEntity.badRequest().body("Null Request");
        }

        Object fromObject = request.get("from");
        Object toObject = request.get("to");
        Object valueObject = request.get("value");
        Object titleObject = request.get("title");
        Object detailObject = request.get("detail");
        if (fromObject == null || toObject == null || valueObject == null || titleObject == null) {
            return ResponseEntity.badRequest().body("Null Request");
        }

        String from = fromObject.toString();
        String to = toObject.toString();
        int value = Integer.valueOf(valueObject.toString());
        String title = titleObject.toString();
        String detail = detailObject.toString();

        transactionService.createTransaction(from, to, value, title, detail);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/transaction/getValue")
    public ResponseEntity transactionGet(@RequestBody JSONObject request) {
        try{
            String from = parseFromFromRequest(request);
            int total = transactionService.getTransactionTotal(from);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", from);
            jsonObject.put("value", total);
            return ResponseEntity.ok().body(jsonObject);
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Transaction get NUll Request");
        }
    }

    @PostMapping("/api/transaction/getDetail")
    public ResponseEntity transactionGetDetail(@RequestBody JSONObject request) {
        try{
            String from = parseFromFromRequest(request);
            List<Transaction> transactionList = transactionService.getAllTransactionByUserName(from);
            return ResponseEntity.ok().body(transactionList);
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Transaction get NUll Request");
        }



    }

    private String parseFromFromRequest(JSONObject request){
        if (request == null) {
            throw new IllegalArgumentException();
        }
        Object fromObject = request.get("from");
        if (fromObject == null) {
            throw new IllegalArgumentException();
        }
        return fromObject.toString();
    }
}
