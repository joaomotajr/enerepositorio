package br.com.eneeyes.archetype.web.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Profile;

/**
 * Created by usr on 16/04/15.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Profile("dev-forward") //-Dspring.profiles.active="dev"
public @interface EnvDevelopmentForward {
}
