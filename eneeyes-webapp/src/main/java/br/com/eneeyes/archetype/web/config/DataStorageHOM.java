package br.com.eneeyes.archetype.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by usr on 11/02/15.
 */
@EnvHomologation
@Configuration
@EnableJpaRepositories("br.com.eneeyes")
@EnableTransactionManagement
@PropertySource("classpath:META-INF/dblocal.properties")
public class DataStorageHOM extends DataStorage {
    public DataStorageHOM() {
        jdbcDriverClassName = "com.mysql.jdbc.Driver";
        jdbcUrl = "jdbc:mysql://localhost:3306/eneeyes?autoReconnect=true";
        jdbcUsername = "adminzHrLVvP";
        jdbcPassword = "SetuX19VtSNE";
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DefaultPersistenceUnitManager pum) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setPersistenceUnitManager(pum);
        emf.setPersistenceUnitName("eneeyes.db.homologation");
        emf.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
        return emf;
    }
}
