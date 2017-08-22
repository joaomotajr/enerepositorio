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
 * Created by usr on 16/04/15.
 */
@EnvDevelopmentForward
@Configuration
@EnableJpaRepositories("br.com.eneeyes")
@EnableTransactionManagement
@PropertySource("classpath:META-INF/dblocal.properties")
public class DataStorageDevForward extends DataStorage {
    public DataStorageDevForward() {
        jdbcDriverClassName = "com.mysql.jdbc.Driver";

        jdbcUrl = "jdbc:mysql://localhost:3306/eneeyes?autoReconnect=true";

        jdbcUsername = "root";

        jdbcPassword = "";
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DefaultPersistenceUnitManager pum) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setPersistenceUnitManager(pum);
        emf.setPersistenceUnitName("eneeyes.db.development.forward");
        emf.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
        return emf;
    }
}
