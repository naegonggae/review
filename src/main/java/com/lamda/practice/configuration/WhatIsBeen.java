package com.lamda.practice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WhatIsBeen {

    @Bean
    public Calculator plus() {
        return new Calculator() {
            @Override
            public int calc(int a, int b) {
                return a + b;
            }
        };
    }
}
