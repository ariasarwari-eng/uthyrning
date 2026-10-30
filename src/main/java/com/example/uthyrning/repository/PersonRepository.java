package com.example.uthyrning.repository;

import com.example.uthyrning.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
