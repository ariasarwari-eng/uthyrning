package com.example.uthyrning.service;

import com.example.uthyrning.model.Person;
import com.example.uthyrning.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PersonService {

    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public List<Person> listAll(){
        return personRepository.findAll();
    }

    // NoSuchElementException - gör att vi kan upptäcka att personen saknas och hantera det på ett tydligare sätt.
    // Men observera att den i sig inte garanterar 404 Not Found – det behöver vi ordna i vår felhantering.
    public Person findById(Long id){
        return personRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Person not found with id: " + id));
    }

    public Person save(Person person){
        return personRepository.save(person);
    }

    public void delete(Long id){
        // om person inte finns kastas NoSuchElementException (ovan)
        Person person = findById(id);
        personRepository.delete(person);
    }
}
