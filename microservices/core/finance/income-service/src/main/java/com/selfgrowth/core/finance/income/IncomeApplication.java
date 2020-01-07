package com.selfgrowth.core.finance.income;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableAsync
@EntityScan("com.selfgrowth.model.finance.income")
@ComponentScan("com.selfgrowth.core.finance.income")
@EnableEurekaClient
@EnableCaching
public class IncomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(IncomeApplication.class, args);
    }

    @Bean
    public Docket swaggerPersonApi10() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.selfgrowth.core.finance.income.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder().version("1.0").title("Income Service API").description("Documentation User API v1.0").build());
    }
}

