package nl.tudelft.oopp.demo.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableJpaRepositories
@PropertySource("application.properties")
@EnableTransactionManagement
public class UserDBConfig {
    @Autowired
    private Environment environment;

    /**
     * Set up the connection to the database.
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("com.mysql.cj.jdbc.Driver"));
        dataSource.setUrl(environment.getProperty("jdbc:mysql://localhost:3306/database_OOPP?createDatabaseIfNotExist=true  "));
        dataSource.setUsername(environment.getProperty("springuser"));
        dataSource.setPassword(environment.getProperty("krabbypatty"));

        return dataSource;
    }
}