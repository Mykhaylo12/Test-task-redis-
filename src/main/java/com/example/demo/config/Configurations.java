package com.example.demo.config;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.keyvalue.core.KeyValueAdapter;
import org.springframework.data.keyvalue.core.KeyValueTemplate;
import org.springframework.data.map.MapKeyValueAdapter;

@Configuration
public class Configurations {

    @Bean("keyValueTemplate")
    public KeyValueTemplate keyValueTemplate() {
        return new KeyValueTemplate(keyValueAdapter());
    }

    @Bean
    public KeyValueAdapter keyValueAdapter() {
        return new MapKeyValueAdapter(ConcurrentHashMap.class);
    }
}