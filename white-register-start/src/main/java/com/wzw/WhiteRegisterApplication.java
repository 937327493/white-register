package com.wzw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;



@SpringBootApplication(scanBasePackages = "com.wzw")
@EnableAsync
public class WhiteRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhiteRegisterApplication.class, args);
    }
}
