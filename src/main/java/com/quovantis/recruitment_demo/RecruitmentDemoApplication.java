package com.quovantis.recruitment_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RecruitmentDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecruitmentDemoApplication.class, args);
    }


}
