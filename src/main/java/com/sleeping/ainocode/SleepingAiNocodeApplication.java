package com.sleeping.ainocode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sleeping.ainocode.mapper")
public class SleepingAiNocodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SleepingAiNocodeApplication.class, args);
    }

}
