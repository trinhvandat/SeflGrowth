package com.selfgrowth.core.finance.income.controller;

import com.selfgrowth.core.finance.income.service.IncomeServiceIml;
import com.selfgrowth.model.finance.income.IncomeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/income")
public class IncomeController {

    private IncomeServiceIml incomeServiceIml;

    @Autowired
    public IncomeController(IncomeServiceIml incomeServiceIml){
        this.incomeServiceIml = incomeServiceIml;
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> create(@RequestParam("earnIncome") double earnIncome,
                                    @RequestParam("passiveIncome") double passiveIncome){
        try {
            IncomeDto.Builder builder = new IncomeDto.Builder()
                    .earnIncome(earnIncome)
                    .passiveIncome(passiveIncome);
            IncomeDto incomeDto = new IncomeDto(builder);
            IncomeDto saved = incomeServiceIml.create(incomeDto);
            return new ResponseEntity<>(saved, HttpStatus.OK);
        } catch (NullPointerException ex){
            System.out.println(ex);
            return new ResponseEntity<>(HttpStatus.SEE_OTHER.getReasonPhrase(), HttpStatus.SEE_OTHER);
        }
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<?> update (@RequestParam("id") int id,
                                     @RequestParam("earnIncome") double earnIncome,
                                     @RequestParam("passiveIncome") double passiveIncome){

        IncomeDto incomeDto = new IncomeDto(id, earnIncome, passiveIncome);
        incomeDto = incomeServiceIml.update(incomeDto);

        if(incomeDto == null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(), HttpStatus.NOT_ACCEPTABLE);
        }
        else {
            return new ResponseEntity<>(incomeDto, HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        IncomeDto incomeDto = incomeServiceIml.delete(id);

        if(incomeDto != null){
            return new ResponseEntity<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable("id") int id){
        IncomeDto incomeDto = incomeServiceIml.findByIdConvertToDto(id);

        if(incomeDto != null){
            return new ResponseEntity<>(incomeDto, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> findAll(){
        List<IncomeDto> incomeDtoList = incomeServiceIml.findAll();
        return new ResponseEntity<>(incomeDtoList, HttpStatus.OK);
    }

}

