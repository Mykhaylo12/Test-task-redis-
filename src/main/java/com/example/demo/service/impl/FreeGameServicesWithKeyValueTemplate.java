package com.example.demo.service.impl;

import com.example.demo.model.FreeGame;
import com.example.demo.service.FreeGameService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Sort;
import org.springframework.data.keyvalue.core.KeyValueTemplate;
import org.springframework.data.keyvalue.core.query.KeyValueQuery;
import org.springframework.stereotype.Service;

@Service("freeGameServicesWithKeyValueTemplate")
@DependsOn("keyValueTemplate")
public class FreeGameServicesWithKeyValueTemplate implements FreeGameService {
    @Autowired
    private KeyValueTemplate keyValueTemplate;

    @Override
    public void save(FreeGame freeGame) {
        keyValueTemplate.insert(freeGame);
    }

    @Override
    public Optional<FreeGame> get(String id) {
        return keyValueTemplate.findById(id, FreeGame.class);
    }

    @Override
    public Iterable<FreeGame> fetchAll() {
        return keyValueTemplate.findAll(FreeGame.class);
    }

    @Override
    public void update(FreeGame freeGame) {
        keyValueTemplate.update(freeGame);
    }

    @Override
    public void delete(String id) {
        keyValueTemplate.delete(id, FreeGame.class);
    }

    @Override
    public Iterable<FreeGame> getSortedListOfFreeGamesSortedByName(int limit) {
        KeyValueQuery query = new KeyValueQuery();
        query.limit(limit).setSort(Sort.by(Sort.Direction.ASC, "name"));
        return keyValueTemplate.find(query, FreeGame.class);
    }
}
