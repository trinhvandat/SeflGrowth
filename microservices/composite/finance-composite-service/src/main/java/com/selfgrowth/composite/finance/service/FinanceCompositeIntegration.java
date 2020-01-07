package com.selfgrowth.composite.finance.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.selfgrowth.model.finance.income.IncomeDto;
import com.selfgrowth.model.util.ServiceUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class FinanceCompositeIntegration {

    private final ServiceUtils util;
    private final RestOperations restTemplate;
    private static final Logger LOG = LoggerFactory.getLogger(FinanceCompositeIntegration.class);


    public FinanceCompositeIntegration(ServiceUtils util, RestOperations restTemplate) {
        this.util = util;
        this.restTemplate = restTemplate;
    }



    //--------------------//
    // CREATE INCOME      //
    //--------------------//

    @HystrixCommand(fallbackMethod = "defaultCreateIncome", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000")}
            )
    public ResponseEntity<IncomeDto> createIncome(IncomeDto incomeDto){
        LOG.debug("will call createIncome with hystrix protection");

        String url = "http://finance-service";
        LOG.debug("createFinance from url " + url);

        ResponseEntity<IncomeDto> result = restTemplate.postForEntity(url, incomeDto, IncomeDto.class);

        LOG.debug("createIncome http-status: " + result.getStatusCode());
        LOG.debug("createIncome body: " + result.getBody());

        IncomeDto incomeDtoResult = result.getBody();
        LOG.debug("createIncome.cnt" + incomeDtoResult.toString());

        return util.createOkResponse(incomeDtoResult);
    }
    /**
     * Fallback method for updateIncome()
     *
     * @return
     */
    public ResponseEntity<IncomeDto> defaultCreateFinance(IncomeDto incomeDto) {
        LOG.debug("Using fallback method for income-service with id: " + incomeDto.getId());

        return util.createResponse(incomeDto, HttpStatus.OK);
    }


    //------------------//
    //UPDATE INCOME     //
    //------------------//

    @HystrixCommand(fallbackMethod = "defaultUpdateIncome", commandProperties = {
            @HystrixProperty(name = "executive.isolation.thread.timeoutInMilliseconds", value = "50000")}
            )
    public ResponseEntity<IncomeDto> updateIncome(IncomeDto incomeDto){
        LOG.debug("will call updateIncome with hystrix protection");

        String url = "http://finance-service";
        LOG.debug("updateFinance from url" + url);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<IncomeDto> requestUpdate = new HttpEntity<>(incomeDto, httpHeaders);
        ResponseEntity<IncomeDto> result = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestUpdate,
                IncomeDto.class
        );

        IncomeDto incomeDtoResult = result.getBody();

        return util.createOkResponse(incomeDtoResult);
    }
    /**
     * fallback method for update income
     */
    public ResponseEntity<IncomeDto> defaultUpdateIncome(IncomeDto incomeDto){
        LOG.debug("Using fallback method for income-service with id: " + incomeDto.getId());
        incomeDto.setId(0);
        return util.createResponse(incomeDto, HttpStatus.OK);
    }

    //-------------//
    //DELETE INCOME//
    //-------------//

    @HystrixCommand(fallbackMethod = "defaultDeleteIncome", commandProperties = {
            @HystrixProperty(name = "executive.isolation.thread.timeoutInMilliseconds", value = "50000")}
    )
    public ResponseEntity<String> deleteIncome(int id){
        LOG.debug("will call deleteIncome with hystrix protection");

        String url = "http://finance-service" + id;
        LOG.debug("deleteIncome from url" + url);

        restTemplate.delete(url);

        return util.createOkResponse(HttpStatus.OK.getReasonPhrase());
    }
    /**
     * fallback method for deleteIncome
     */
    public ResponseEntity<String> defaultDeleteIncome(int id){
        LOG.debug("Using fallback method for income-service with id: " + id);
        return util.createResponse("ERROR", HttpStatus.OK);
    }

    //--------------//
    //GET INCOME    //
    //--------------//

    @HystrixCommand(fallbackMethod = "defaultGetIncome", commandProperties = {
            @HystrixProperty(name = "executive.isolation.thread.timeoutInMilliseconds", value = "50000")
    })
    public ResponseEntity<IncomeDto> getIncome(int id){
        LOG.debug("will call getIncome with hystrix protection");

        String url = "http://finance-service" + id;
        LOG.debug("getIncome from url: " + url);

        ResponseEntity<IncomeDto> result = restTemplate.getForEntity(url, IncomeDto.class);

        LOG.debug("getIncome http-status: " + result.getStatusCode() );
        LOG.debug("getIncome body: " + result.getBody());

        IncomeDto incomeDtoResult = result.getBody();
        LOG.debug("getIncome.cnt: " + result.getBody());

        return util.createOkResponse(incomeDtoResult);
    }
    /**
     * fallback method getIncome
     */
    public ResponseEntity<IncomeDto> defaultGetIncome(int id){
        LOG.debug("Using fallback method for income-service with id = " + id);

        IncomeDto dto = new IncomeDto();
        dto.setId(0);
        return util.createResponse(dto, HttpStatus.OK);
    }



    //-----------------//
    //  GET ALL INCOME //
    //-----------------//
    @HystrixCommand(fallbackMethod = "defaultGetAllIncome", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "50000")
    })
    public ResponseEntity<List<IncomeDto>> getAllIncome(){
        LOG.debug("will call getAllIncome with hystrix protection");

        String url = "http://finance-composite-service";
        LOG.debug("getAllIncome from url " + url);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<String> result = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                String.class
        );

        LOG.debug("getAllIncome http-status: " + result.getStatusCode());
        LOG.debug("getAllIncome body " + result.getBody());

        List<IncomeDto> resultDtoList = response2Income(result);
        LOG.debug("getAllIncome.cnt " + resultDtoList.toString());

        return util.createOkResponse(resultDtoList);
    }
    /**
     * fallback method getAllIncome
     */
    public ResponseEntity<List<IncomeDto>> defaultGetAllIncome(){
        LOG.debug("Using fallback method for income-service");

        return util.createResponse(null, HttpStatus.OK);
    }



    private List<IncomeDto> response2Income(ResponseEntity<String> response) {

        try{
            ObjectMapper mapper = new ObjectMapper();
            List<IncomeDto> locals = Arrays.asList(mapper.readValue(response.getBody(), IncomeDto[].class));

            return locals;
        }catch (IOException e){
            LOG.debug("IO-err. Failed to read JSON " + e);
            throw new RuntimeException(e);
        }catch (RuntimeException re){
            LOG.debug("RTE-err. Failed to read  JSON " + re);
            throw re;
        }
    }
}
