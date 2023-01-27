package dev.luucx7.seitabot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SeitaBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeitaBotApplication.class, args);
    }

}
