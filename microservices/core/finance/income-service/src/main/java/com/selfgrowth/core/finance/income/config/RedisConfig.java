package com.selfgrowth.core.finance.income.config;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public CacheManager cacheManager(){
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        Cache incomes = new ConcurrentMapCache("incomeCache");
        Cache income = new ConcurrentMapCache("allIncomeCache");

        cacheManager.setCaches(Arrays.asList(incomes, income));
        return cacheManager;
    }

}

