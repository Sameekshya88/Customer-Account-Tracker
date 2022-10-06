package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bank.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

}
