package org.openpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OpenPayTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(OpenPayTestApplication.class, args);
    }
}