package br.com.eneeyes.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.eneeyes.archetype.web.config.EnvHomologation;

/**
 * Created by usr on 16/04/15.
 */
@Configuration
@EnvHomologation
public class AppEnvConfigHom {
    @Bean
    public String appUrl() { return "http://server01-4fuse.rhcloud.com/"; }
}
