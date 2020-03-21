package com.myntra.order.service;

import com.myntra.order.model.Item;
import com.myntra.order.repository.ItemRegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ItemRegistryService {

    @Autowired
    private ItemRegistryRepository repository;

    public Item fetchItemById(String itm) {
        Item item = null;
        try {
            item = repository.findById(itm).get();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return item;
    }
}
