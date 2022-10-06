package com.bank.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bank.entities.Account;
import com.bank.entities.Customer;
import com.bank.service.CustomerService;
@RestController
public class CustomerController {

	   @Autowired
	   CustomerService customerservice;
	   @PostMapping("/api/customers")
	   public ResponseEntity<Customer> createCustomer(@RequestBody Customer cust) {
	      return customerservice.createCustomer(cust);
	   }
	   @PostMapping(value = "api/customers/{id}", consumes = "application/json")
	   public Customer create(@PathVariable int id, @RequestBody Account acc) {
	      return customerservice.create(id, acc);
	   }
	   @GetMapping("/api/customers")
	   public List<Customer> getCustomers() {
	      List<Customer> customers = customerservice.getCustomers();
	      System.out.println("Controller ser"+customers);
	      return customers;
	   }
	   @GetMapping(value = "/api/customers/{customer_id}")
	   public Customer getAccount(@PathVariable int customer_id) {// @Pathvariable refers to local variable
	      Customer customer = customerservice.getCustomer(customer_id);
	      return customer;
	   }
	   @PutMapping("/api/customers/{customer_id}")
	   public Customer updateCustomer(@PathVariable int customer_id, @RequestBody Customer cust) {
	      Customer customerUpdate = customerservice.updateCustomer(customer_id, cust);
	      return customerUpdate;
	   }
	   @DeleteMapping("/api/customers/{customer_id}")
	   public Customer deleteCustomer(@PathVariable int customer_id) {
	      Customer customerDelete = customerservice.deleteCustomer(customer_id);
	      return customerDelete;
	   }
	   @PostMapping(value = "/customeraccount/{id}", consumes = "application/json")
	   public ResponseEntity<Object> saveCustomer(@PathVariable int id, @RequestBody Account account)
	   {
	      Customer entity = customerservice.saveCustomer(id, account);
	      if (entity != null)
	         return ResponseEntity.status(HttpStatus.CREATED)
	               .body("Maaped Account with customer successfully");
	      else {
	         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer doesn't exist");
	      }
	   }
	
}
