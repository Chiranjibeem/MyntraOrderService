package com.myntra.order.repository;

import com.myntra.order.model.BookTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<BookTable,Integer> {
}
