package com.selfgrowth.core.finance.income.service;


import com.selfgrowth.core.finance.income.repository.IncomeRepository;
import com.selfgrowth.model.finance.income.Income;
import com.selfgrowth.model.finance.income.IncomeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class IncomeServiceIml implements IncomeService {

    private IncomeRepository repository;

    @Autowired
    public IncomeServiceIml(IncomeRepository repository){
        this.repository = repository;
    }

    @Override
    @Caching(
            put = {@CachePut(value = "incomeCache", key = "#incomeDto.id")},
            evict = {@CacheEvict(value = "allIncomeCache", allEntries = true)}
    )
    public IncomeDto create(IncomeDto incomeDto) {

        Income income = findById(incomeDto.getId());
        if(income == null){
            Income persisted = Income.getBuilder()
                    .earnIncome(incomeDto.getEarnIncome())
                    .passiveIncome(incomeDto.getPassiveIncome())
                    .build();
            persisted = repository.save(persisted);
            return convertToDTO(persisted);
        }
        else {
            return null;
        }
    }


    @Override
    public Income findById(int id) {
        Income income = repository.findById(id).orElse(null);
        return income;
    }

    @Override
    @Cacheable(value = "incomeCache", key = "#id")
    public IncomeDto findByIdConvertToDto(int id) {
        Income income = repository.findById(id).orElse(null);
        return convertToDTO(income);
    }

    @Override
    @Cacheable(value = "allIncomeCache", unless = "#result.size() == 0")
    public List<IncomeDto> findAll() {
        List<Income> incomes = repository.findAll();
        return convertToDTOs(incomes);
    }

    @Override
    @Caching(
            put = {@CachePut(value = "incomeCache", key = "#user.id")},
            evict = {@CacheEvict(value = "allIncomeCache", allEntries = true)}
    )
    public IncomeDto update(IncomeDto user) {

        Income updated = findById(user.getId());

        if(updated != null){
            updated.setEarnIncome(user.getEarnIncome());
            updated.setPassiveIncome(user.getPassiveIncome());

            repository.save(updated);
        }
        return convertToDTO(updated);
    }

    @Override
    @Caching(
            evict = {@CacheEvict(value = "incomeCache", key = "#id"),
                    @CacheEvict(value = "allIncomeCache", allEntries = true)

            }

    )
    public IncomeDto delete(int id) {
        Income deleter = findById(id);

        if(deleter != null){
            repository.deleteById(deleter.getId());
            return convertToDTO(deleter);
        }
        return null;
    }

    private List<IncomeDto> convertToDTOs(List<Income> models) {
        if (models != null)
            return models.stream().map(this::convertToDTO).collect(toList());
        else return null;
    }

    private IncomeDto convertToDTO(Income model) {
        IncomeDto dto = new IncomeDto();
        if (model != null) {
            dto.setId(model.getId());
            dto.setEarnIncome(model.getEarnIncome());
            dto.setPassiveIncome(model.getPassiveIncome());
            return dto;
        } else return null;
    }
}
