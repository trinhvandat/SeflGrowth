package com.selfgrowth.core.finance.income.service;

import com.selfgrowth.model.finance.income.Income;
import com.selfgrowth.model.finance.income.IncomeDto;

import java.util.List;

public interface IncomeService {

    IncomeDto create(IncomeDto incomeDto);

    Income findById(int id);

    IncomeDto findByIdConvertToDto(int id);

    List<IncomeDto> findAll();

    IncomeDto update(IncomeDto user);

    IncomeDto delete(int id);
}
