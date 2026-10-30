package com.example.uthyrning.service;

import com.example.uthyrning.model.Item;
import com.example.uthyrning.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ItemService {

    private ItemRepository itemRepository;
    public ItemService(ItemRepository itemRepository) {

        this.itemRepository = itemRepository;
    }

    public List<Item> listAll(){
        return itemRepository.findAll();
    }

    public Item findById(Long id){
        //om varan ej finns kastas NoSuchElementException, som din GlobalExceptionHandler
        // redan hanterar med 404 Not Found.
        return itemRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Item Not Found with id: " + id));
    }

    public Item save(Item item){
        return itemRepository.save(item);
    }


    public void deleteById(Long id){
        Item item = findById(id);
        itemRepository.delete(item);
    }
}
