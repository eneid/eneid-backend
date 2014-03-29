package io.github.eneid.migration;

import com.googlecode.flyway.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Component
public class Migrator {

    private final Flyway migrator;

    @Autowired
    public Migrator(DataSource dataSource) {
        migrator = new Flyway();
        migrator.setDataSource(dataSource);
    }

    @PostConstruct
    public void migrate() {
        migrator.migrate();
    }

}
