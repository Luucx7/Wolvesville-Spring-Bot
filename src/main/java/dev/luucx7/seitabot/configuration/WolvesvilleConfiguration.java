package dev.luucx7.seitabot.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "wolvesville")
public class WolvesvilleConfiguration {
    @Getter
    @Setter
    private String token;

    @Getter
    @Setter
    private String clanId;
}
