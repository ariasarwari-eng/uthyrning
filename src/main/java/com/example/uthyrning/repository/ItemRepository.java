package com.example.uthyrning.repository;

import com.example.uthyrning.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
