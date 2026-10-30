package com.example.uthyrning.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull (message = "Person is required")
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @NotNull (message = "Item is required")
    @ManyToOne
    @JoinColumn(name= "item_id", nullable = false)
    private Item item;

    @NotNull (message = "Start date is required") // om tom kastar MethodArgumentNotValidException, måste hanteras, drf meddelande ej syns vid 400
    private LocalDate startDate;

    private LocalDate endDate;

    public Rental() {}

    public Rental(Person person, Item item, LocalDate startDate, LocalDate endDate) {
        this.person = person;
        this.item = item;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "Item is required") Item getItem() {
        return item;
    }

    public void setItem(@NotNull(message = "Item is required") Item item) {
        this.item = item;
    }

    public @NotNull(message = "Person is required") Person getPerson() {
        return person;
    }

    public void setPerson(@NotNull(message = "Person is required") Person person) {
        this.person = person;
    }

    public @NotNull(message = "Start date is required") LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(@NotNull(message = "Start date is required") LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "endDate=" + endDate +
                ", id=" + id +
                ", person=" + person +
                ", item=" + item +
                ", startDate=" + startDate +
                '}';
    }
}
