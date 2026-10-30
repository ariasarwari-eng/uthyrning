package com.example.uthyrning.service;

import com.example.uthyrning.model.Item;
import com.example.uthyrning.model.Person;
import com.example.uthyrning.model.Rental;
import com.example.uthyrning.repository.ItemRepository;
import com.example.uthyrning.repository.PersonRepository;
import com.example.uthyrning.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RentalService {

    private RentalRepository rentalRepository;
    private ItemRepository itemRepository;
    private PersonRepository personRepository;

    public RentalService(
            RentalRepository rentalRepository,
            ItemRepository itemRepository,
            PersonRepository personRepository) {
        this.rentalRepository = rentalRepository;
        this.itemRepository = itemRepository;
        this.personRepository = personRepository;
    }

    public List<Rental> listAll() {
        return rentalRepository.findAll();
    }

    public Rental findById(Long id) {
        return rentalRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Rental not found with id: " + id));
    }

    public Rental returnItem(Long id){

        Rental rental = rentalRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException ("Rental not found"));

        if(rental.getEndDate() != null){
            throw new IllegalStateException ("This rental has already ended.");
        }
        rental.setEndDate(LocalDate.now());
        return rentalRepository.save(rental);
    }

    public Rental save(Rental rental) {

        if(rental.getStartDate() != null &&
                rental.getEndDate() != null &&
                rental.getEndDate().isBefore(rental.getStartDate())) {

            // IllegalArgumentException passar när ett argument som skickas in är ogiltigt, exempelvis
            // att slutdatumet ligger före startdatumet.
            throw new IllegalArgumentException(
                    "End date cannot be before start date."
            );
        }

        // IllegalStateException passar när en operation inte kan genomföras på grund av objektets aktuella
        // tillstånd, exempelvis att varan redan är uthyrd.
        Person person = personRepository.findById(rental.getPerson().getId())
                .orElseThrow(()-> new NoSuchElementException("Person not found"));

        Item item = itemRepository.findById(rental.getItem().getId())
                .orElseThrow(()-> new NoSuchElementException("Item not found"));

        rental.setPerson(person);
        rental.setItem(item);

        List <Rental> activeRentals =
                rentalRepository.findByItemIdAndEndDateIsNull(rental.getItem().getId());

        if (!activeRentals.isEmpty()) {
            throw new IllegalStateException("This Item is already rented out.");
        }
        return rentalRepository.save(rental);
    }
}
