package io.github.eneid.migration;

import com.googlecode.flyway.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class Migrator {

    @Bean
    public Flyway flyWay(DataSource dataSource) {
        Flyway migrator = new Flyway();
        migrator.setDataSource(dataSource);
        return migrator;
    }

}
