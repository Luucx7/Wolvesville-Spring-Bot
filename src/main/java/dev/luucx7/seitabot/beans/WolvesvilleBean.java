package dev.luucx7.seitabot.beans;

import dev.luucx7.seitabot.configuration.WolvesvilleConfiguration;
import dev.luucx7.seitabot.wolvesville.WolvesvilleAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class WolvesvilleBean {

    private final WolvesvilleConfiguration wolvesvilleConfiguration;

    @Autowired
    public WolvesvilleBean(WolvesvilleConfiguration wolvesvilleConfiguration) {
        this.wolvesvilleConfiguration = wolvesvilleConfiguration;
    }

    @Bean
    public WolvesvilleAPI createWOVApi() {
        return new WolvesvilleAPI(wolvesvilleConfiguration.getToken(), wolvesvilleConfiguration.getClanId());
    }
}
