package com.myntra.order.controller;

import com.myntra.order.model.Item;
import com.myntra.order.service.ItemRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemRegistryController {

    @Autowired
    private ItemRegistryService service;

    @RequestMapping(value = "addItem/{itemId}")
    public Item getItems(@PathVariable(name = "itemId") String itemId) throws Exception {
        return service.fetchItemById(itemId);
    }

}
