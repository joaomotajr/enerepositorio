package br.com.eneeyes.archetype.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnvDevelopmentPostgree
@Configuration
@EnableJpaRepositories("br.com.eneeyes")
@EnableTransactionManagement
@PropertySource("classpath:META-INF/dblocal.properties")
public class DataStoragePostgreeDEV extends DataStorage {

    public DataStoragePostgreeDEV() {
        jdbcDriverClassName = "org.postgresql.Driver";

        jdbcUrl = "jdbc:postgresql://localhost:5432/eneDb?autoReconnect=true";
     
        jdbcUsername = "root";

        jdbcPassword = "";
    }

	@Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DefaultPersistenceUnitManager pum) {
    	LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
    	emf.setPersistenceUnitManager(pum);
    	emf.setPersistenceUnitName("eneeyes.db.developmentPostgree");
    	emf.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
    	return emf;
    }
}
