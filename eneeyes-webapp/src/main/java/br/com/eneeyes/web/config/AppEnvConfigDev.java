package br.com.eneeyes.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.eneeyes.archetype.web.config.EnvDevelopment;
import br.com.eneeyes.archetype.web.config.EnvDevelopmentPostgree;

/**
 * Created by usr on 16/04/15.
 */
@Configuration
@EnvDevelopment
@EnvDevelopmentPostgree
public class AppEnvConfigDev {
    @Bean
    public String appUrl() { return "http://localhost:8080/"; }
}
