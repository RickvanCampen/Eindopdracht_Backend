package com.example.eindopdracht_backend_ipmroved;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;



@SpringBootApplication
@EntityScan("com.example.eindopdracht_backend_ipmroved.model")
public class EindopdrachtBackendIpmrovedApplication {

    public static void main(String[] args) {
        SpringApplication.run(EindopdrachtBackendIpmrovedApplication.class, args);
    }

}
