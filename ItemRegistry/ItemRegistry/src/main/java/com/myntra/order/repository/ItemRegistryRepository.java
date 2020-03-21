package com.myntra.order.repository;

import com.myntra.order.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRegistryRepository extends JpaRepository<Item, String> {
}
