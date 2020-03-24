package com.example.demo.service.impl;

import com.example.demo.model.GrossingGame;
import com.example.demo.service.GrossingGameService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Sort;
import org.springframework.data.keyvalue.core.KeyValueTemplate;
import org.springframework.data.keyvalue.core.query.KeyValueQuery;
import org.springframework.stereotype.Service;

@Service("grossingGameServicesWithKeyValueTemplate")
@DependsOn("keyValueTemplate")
public class GrossingGameServicesWithKeyValueTemplate implements GrossingGameService {
    @Autowired
    private KeyValueTemplate keyValueTemplate;

    @Override
    public void save(GrossingGame grossingGame) {
        keyValueTemplate.insert(grossingGame);
    }

    @Override
    public Optional<GrossingGame> get(String id) {
        return keyValueTemplate.findById(id, GrossingGame.class);
    }

    @Override
    public Iterable<GrossingGame> fetchAll() {
        return keyValueTemplate.findAll(GrossingGame.class);
    }

    @Override
    public void update(GrossingGame grossingGame) {
        keyValueTemplate.update(grossingGame);
    }

    @Override
    public void delete(String id) {
        keyValueTemplate.delete(id, GrossingGame.class);
    }

    @Override
    public Iterable<GrossingGame> getSortedListOfGrossingGamesSortedByName(int limit) {
        KeyValueQuery query = new KeyValueQuery();
        query.limit(limit).setSort(Sort.by(Sort.Direction.ASC, "name"));
        return keyValueTemplate.find(query, GrossingGame.class);
    }
}
