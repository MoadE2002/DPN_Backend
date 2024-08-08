package com.Dpnetworks.dp.controller;

import com.Dpnetworks.dp.model.Expense;
import com.Dpnetworks.dp.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@RestController
@RequestMapping("/expense")
public class ExpensesController {

    private final ExpenseService expenseService;

    @Autowired
    public ExpensesController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Expense>> getAllExpenses() {
        List<Expense> expenses = expenseService.finfAllExpenses();
        if (expenses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

//    @GetMapping("/byCollabIdAndMonth")
//    public ResponseEntity<List<Expense>> getExpensesByCollabIdAndMonth(
//            @RequestParam Long collabId,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
//
//        List<Expense> expenses = expenseService.findByCollaboratorIdAndMonth(collabId, date);
//        if (expenses.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(expenses, HttpStatus.OK);
//    }

}
