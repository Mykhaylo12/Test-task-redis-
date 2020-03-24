package com.example.demo.service.impl;

import com.example.demo.model.PaidGame;
import com.example.demo.service.PaidGameService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Sort;
import org.springframework.data.keyvalue.core.KeyValueTemplate;
import org.springframework.data.keyvalue.core.query.KeyValueQuery;
import org.springframework.stereotype.Service;

@Service("paidGameServicesWithKeyValueTemplate")
@DependsOn("keyValueTemplate")
public class PaidGameServicesWithKeyValueTemplate implements PaidGameService {

    @Autowired
    private KeyValueTemplate keyValueTemplate;

    @Override
    public void save(PaidGame paidGame) {
        keyValueTemplate.insert(paidGame);
    }

    @Override
    public Optional<PaidGame> get(String id) {
        return keyValueTemplate.findById(id, PaidGame.class);
    }

    @Override
    public Iterable<PaidGame> fetchAll() {
        return keyValueTemplate.findAll(PaidGame.class);
    }

    @Override
    public void update(PaidGame paidGame) {
        keyValueTemplate.update(paidGame);
    }

    @Override
    public void delete(String id) {
        keyValueTemplate.delete(id, PaidGame.class);
    }

    @Override
    public Iterable<PaidGame> getSortedListOfPaidGamesSortedByName(int limit) {
        KeyValueQuery query = new KeyValueQuery();
        query.limit(limit).setSort(Sort.by(Sort.Direction.ASC, "name"));
        return keyValueTemplate.find(query, PaidGame.class);
    }
}
