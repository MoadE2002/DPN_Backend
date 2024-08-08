package com.Dpnetworks.dp.repo;

import com.Dpnetworks.dp.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {


    public List<Expense> findByCollaboratorIdAndMonth(Long collaboratorId, Date month);
    public List<Expense> findAll();
}
