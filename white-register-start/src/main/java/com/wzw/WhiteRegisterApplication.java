package com.wzw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.wzw")
public class WhiteRegisterApplication {
    public static void main(String[] args) {
        SpringApplication.run(WhiteRegisterApplication.class, args);
    }
}
