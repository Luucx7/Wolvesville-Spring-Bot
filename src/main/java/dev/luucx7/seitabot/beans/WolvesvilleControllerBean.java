package dev.luucx7.seitabot.beans;

import dev.luucx7.seitabot.controllers.WolvesvilleController;
import dev.luucx7.seitabot.wolvesville.WolvesvilleAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WolvesvilleControllerBean {

    private final WolvesvilleAPI api;

    @Autowired
    public WolvesvilleControllerBean(WolvesvilleAPI api) {
        this.api = api;
    }

    @Bean
    public WolvesvilleController createWOVController() {
        return new WolvesvilleController(this.api);
    }
}
