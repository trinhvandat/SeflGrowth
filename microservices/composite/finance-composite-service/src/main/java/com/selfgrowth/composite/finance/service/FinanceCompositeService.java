package com.selfgrowth.composite.finance.service;


import com.selfgrowth.model.finance.income.IncomeDto;
import com.selfgrowth.model.util.ServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@RestController
@RequestMapping("api/v1/financeComposite")
public class FinanceCompositeService {

    private FinanceCompositeIntegration integration;
    private ServiceUtils utils;

    private static final Logger LOG = LoggerFactory.getLogger(FinanceCompositeService.class);

    @Autowired
    public FinanceCompositeService(FinanceCompositeIntegration integration, ServiceUtils utils){
        this.integration = integration;
        this.utils = utils;
    }

    /**
     * POST API
     * @param incomeDto
     * @return
     */
    @PostMapping(value = "/income", produces = "application/json")
    public ResponseEntity<IncomeDto> createIncome(@RequestBody IncomeDto incomeDto){
        IncomeDto createIncomeResult = createBasicIncome(incomeDto);
        return utils.createOkResponse(createIncomeResult);
    }

    private IncomeDto createBasicIncome(IncomeDto incomeDto){
        ResponseEntity<IncomeDto> responseEntity = integration.createIncome(incomeDto);
        IncomeDto createIncomeResult = null;
        if(!responseEntity.getStatusCode().is2xxSuccessful()){
            LOG.debug("call to createBasicIncome failed: " + responseEntity.getStatusCode());
        }
        else {
            createIncomeResult = responseEntity.getBody();
        }

        return createIncomeResult;
    }


    /**
     * PUT API
     */
    @PutMapping(value = "/income", produces = "application/json")
    public ResponseEntity<IncomeDto> updateIncome(@RequestBody IncomeDto incomeDto){
        IncomeDto updateIncomeResult = updateBasicIncome(incomeDto);
        return utils.createOkResponse(updateIncomeResult);
    }

    private IncomeDto updateBasicIncome(IncomeDto incomeDto) {
        ResponseEntity<IncomeDto> responseEntity = integration.updateIncome(incomeDto);
        IncomeDto updateIncomeResult = null;
        if(!responseEntity.getStatusCode().is2xxSuccessful()){
            LOG.debug("call to updateBasicIncome failed: " + responseEntity.getStatusCode());
        }
        else {
            updateIncomeResult = responseEntity.getBody();
        }

        return updateIncomeResult;
    }

    /**
     * DELETE API
     */


}
