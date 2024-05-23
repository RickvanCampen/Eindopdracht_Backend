package com.example.eindopdracht_backend_ipmroved.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.eindopdracht_backend_ipmroved.repository")
public class JpaConfig {

}
