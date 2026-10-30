package com.example.uthyrning.controller;

import com.example.uthyrning.model.Rental;
import com.example.uthyrning.service.RentalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rental")
public class RentalController {

    private RentalService rentalService;
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/")
    public List<Rental> getAll() {
        return rentalService.listAll();
    }

    @GetMapping("/{id}")
    public Rental getOne(@PathVariable Long id){
        return rentalService.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@Valid @RequestBody Rental rental){
        Rental savedRental = rentalService.save(rental);
        return new ResponseEntity<>(savedRental,HttpStatus.CREATED);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<?> returnItem(@PathVariable Long id){
        Rental returnedRental = rentalService.returnItem(id);
        return new ResponseEntity<>(returnedRental, HttpStatus.OK);
    }
}
