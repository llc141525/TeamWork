package org.example.teamwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TeamWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamWorkApplication.class, args);
    }

}
