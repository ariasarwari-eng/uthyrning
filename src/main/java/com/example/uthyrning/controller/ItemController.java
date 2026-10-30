package com.example.uthyrning.controller;

import com.example.uthyrning.model.Item;
import com.example.uthyrning.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ItemController {

    private ItemService itemService;
    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    // hämta alla saker
    @GetMapping("item/")
    public List<Item> getAll(){
        return itemService.listAll();
    }

    // hämta en sak
    @GetMapping("item/{id}")
    public Item getOne(@PathVariable Long id){
        return itemService.findById(id);
    }

    // skapa en sak
    @PostMapping("item/")
    public ResponseEntity<?> save(@Valid @RequestBody Item item){
        itemService.save(item);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    // uppdatera en sak
    @PutMapping("item/{id}")
    public Item update(@Valid @RequestBody Item item, @PathVariable Long id){
        Item existingItem = itemService.findById(id);
        existingItem.setName(item.getName());
        existingItem.setPrice(item.getPrice());
        existingItem.setDescription(item.getDescription());
        return  itemService.save(existingItem);
    }

    //ta bort en sak
    @DeleteMapping("item/{id}")
    public void deleteById(@PathVariable Long id){
        itemService.deleteById(id);
    }

}
