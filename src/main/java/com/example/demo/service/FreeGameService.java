package com.example.demo.service;

import com.example.demo.model.FreeGame;
import java.util.Optional;

public interface FreeGameService {
    void save(FreeGame freeGame);

    Optional<FreeGame> get(String id);

    Iterable<FreeGame> fetchAll();

    void update(FreeGame freeGame);

    void delete(String id);

    Iterable<FreeGame> getSortedListOfFreeGamesSortedByName(int limit);
}

