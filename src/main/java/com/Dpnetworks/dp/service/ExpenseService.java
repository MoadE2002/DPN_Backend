package com.Dpnetworks.dp.service;

import com.Dpnetworks.dp.model.Expense;
import com.Dpnetworks.dp.repo.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> findByCollaboratorIdAndMonth(Long collabId, Date date) {
        return expenseRepository.findByCollaboratorIdAndMonth(collabId, date);
    }

    public List<Expense> finfAllExpenses() {
        return expenseRepository.findAll();
    }
}
