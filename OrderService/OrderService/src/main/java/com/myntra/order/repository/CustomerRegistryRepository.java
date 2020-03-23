package com.myntra.order.repository;

import com.myntra.order.model.Customer;
import com.myntra.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRegistryRepository extends JpaRepository<Customer,String>{

    @Query(value = "select * from M_ORD_TRAN_CUST where cust_id = :custId",nativeQuery = true)
    public List<Customer> findAllOrderByCustomer(String custId);
}
