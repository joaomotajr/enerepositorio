package br.com.eneeyes.archetype.web.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;

public class DataStorage {

    protected String jdbcDriverClassName;

    protected String jdbcUrl;

    protected String jdbcUsername;

    protected String jdbcPassword;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(jdbcDriverClassName);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(jdbcUsername);
        dataSource.setPassword(jdbcPassword);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

    @Bean
    public DefaultLobHandler defaultLobHandler(DataSource dataSource) {
        return new DefaultLobHandler();
    }

    @Bean
    public PersistenceAnnotationBeanPostProcessor pnpp() {
    	return new PersistenceAnnotationBeanPostProcessor();
    }
    
    @Bean
    public DefaultPersistenceUnitManager pum() {
    	DefaultPersistenceUnitManager pum = new DefaultPersistenceUnitManager();
    	pum.setPersistenceXmlLocation("classpath*:META-INF/persistence.xml");
    	return pum;
    }
    
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
    	JpaTransactionManager tmg = new JpaTransactionManager();
    	tmg.setEntityManagerFactory(emf);
    	return tmg;
    }   

}
