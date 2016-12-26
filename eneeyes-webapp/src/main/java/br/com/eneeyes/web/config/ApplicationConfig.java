package br.com.eneeyes.web.config;

import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
//@ImportResource({"classpath:camel-context.xml"})
@PropertySource("classpath:camel.properties")
@EnableScheduling
public class ApplicationConfig {
    @Bean
    public String ediPath() {
        return "/var/lib/openshift/55fb80510c1e6614f7000151/app-root/data/eneeyes.recebidos/";
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setDefaultEncoding("utf-8");
        commonsMultipartResolver.setMaxUploadSize(50000000);
        return commonsMultipartResolver;
    }

    @Bean
    public Session mailSession() {
        final Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", "mail.dcm.net.br");
        properties.setProperty("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.setProperty("mail.user", "cadastro@dcm.net.br");
        properties.setProperty("mail.password", "!nov2@15");

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(properties.getProperty("mail.user"), properties.getProperty("mail.password"));
                    }
                });

        return session;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper;
    }
}
