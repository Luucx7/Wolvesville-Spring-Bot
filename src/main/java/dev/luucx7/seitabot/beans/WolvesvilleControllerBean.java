package dev.luucx7.seitabot.beans;

import dev.luucx7.seitabot.controllers.WolvesvilleController;
import dev.luucx7.seitabot.wolvesville.WolvesvilleAPI;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class WolvesvilleControllerBean {

    @Getter
    private final WolvesvilleAPI api;

    @Autowired
    public WolvesvilleControllerBean(WolvesvilleAPI api) {
        this.api = api;
    }

    @Bean()
    @Primary
    public WolvesvilleController createWOVController() {
        return new WolvesvilleController(this.api);
    }
}
