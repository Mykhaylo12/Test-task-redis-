package com.example.demo.service;

import com.example.demo.model.PaidGame;
import java.util.Optional;

public interface PaidGameService {

    void save(PaidGame paidGame);

    Optional<PaidGame> get(String id);

    Iterable<PaidGame> fetchAll();

    void update(PaidGame paidGame);

    void delete(String id);

    Iterable<PaidGame> getSortedListOfPaidGamesSortedByName(int limit);
}