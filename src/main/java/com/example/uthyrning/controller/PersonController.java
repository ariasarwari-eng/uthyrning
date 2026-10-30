package com.example.uthyrning.controller;

import com.example.uthyrning.model.Person;
import com.example.uthyrning.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService){

        this.personService = personService;
    }

    @GetMapping("person/")
    public List<Person> getAll(){
        return personService.listAll();
    }

    @GetMapping("person/{id}")
    public Person getOne(@PathVariable Long id){
        return personService.findById(id);
    }

    @PostMapping ("person/")
    public ResponseEntity<?>  save(@Valid @RequestBody Person person){
        personService.save(person);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    @PutMapping ("person/{id}")
    public Person update(@PathVariable Long id, @Valid @RequestBody Person person){
        Person existingPerson = personService.findById(id);
        existingPerson.setName(person.getName());
        existingPerson.setEmail(person.getEmail());
        return personService.save(existingPerson);
    }

    @DeleteMapping ("person/{id}")
    public void delete (@PathVariable Long id) {
        personService.delete(id);
    }

}
