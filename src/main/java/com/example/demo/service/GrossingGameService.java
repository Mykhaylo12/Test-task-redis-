package com.example.demo.service;

import com.example.demo.model.GrossingGame;
import java.util.Optional;

public interface GrossingGameService {
    void save(GrossingGame grossingGame);

    Optional<GrossingGame> get(String id);

    Iterable<GrossingGame> fetchAll();

    void update(GrossingGame grossingGame);

    void delete(String id);

    Iterable<GrossingGame> getSortedListOfGrossingGamesSortedByName(int limit);
}
