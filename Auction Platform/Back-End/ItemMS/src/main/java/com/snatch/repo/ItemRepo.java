package com.snatch.repo;

import com.snatch.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepo extends JpaRepository<Item, Integer> {
    Optional<List<Item>> findByAddedBy(String userId);
}
