package com.selfgrowth.core.finance.income.repository;


import com.selfgrowth.model.finance.income.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Integer> {

    /**
     * delete a income entry from database.
     * @param id
     */
    void deleteById(int id);

    void delete(Income deleter);

    /**
     * find all income entries from database.
     * @return
     */
    List<Income> findAll();

    Optional<Income> findById(int id);

    /**
     * save user into database
     */
    Income save(Income saved);
}

