package com.example.demo.config;

import com.example.demo.repository.DbSeeder;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    private final DbSeeder dbSeeder;

    public ScheduledTasks(DbSeeder dbSeeder) {
        this.dbSeeder = dbSeeder;
    }
}
