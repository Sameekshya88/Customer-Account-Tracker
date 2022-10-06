package com.bank.service;


import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.bank.entities.Account;
import com.bank.entities.Customer;
import com.bank.exception.BalanceNotFoundException;
import com.bank.exception.NotFoundException;
import com.bank.repository.AccountRepository;
import com.bank.repository.CustomerRepository;

@Service
public class CustomerService {

	   // private static List<Customer> customers = new ArrayList<Customer>();
	   // static int counter=2000;
	   @Autowired
	   CustomerRepository repository;
	   @Autowired
	   AccountRepository repo;
	   public ResponseEntity<Customer> createCustomer(Customer customer) {
	      Customer createCustomer = repository.save(customer);
	      return new ResponseEntity<Customer>(createCustomer, HttpStatus.CREATED);
	   }
	   public List<Customer> getCustomers() {
	      List<Customer> customers = (List<Customer>) repository.findAll();
	      //System.out.println("customer 11111--->"+customers);
	      return customers;
	   }
	   public Customer getCustomer(int customerid) {
	      Optional<Customer> customers = repository.findById(customerid);
	      if (customers.isEmpty()) {
	         throw new NotFoundException("Customer doesn't exists-->" + customerid);
	      }
	      Customer customer = customers.get();
	      if (customer == null) {
	         throw new NotFoundException("Customer doesn't exists" + customerid);
	      }
	      return customer;
	   }
	   public Customer updateCustomer(int id, Customer cust) {
	      Optional<Customer> customers = repository.findById(id);
	      if (customers.isEmpty()) {
	         throw new NotFoundException("Customer doesn't exists-->#" + id);
	      }
	      Customer customer = customers.get();
	      if (customer == null) {
	         throw new NotFoundException("Customer doesn't exists" + id);
	      }
	      customer.setFirstName(cust.getFirstName());
	      customer.setLastName(cust.getLastName());
	      customer.setEmail(cust.getEmail());
	      return repository.save(customer);
	   }
	   public Customer deleteCustomer(int customer_id) {
	      Optional<Customer> customers = repository.findById(customer_id);
	      Customer deleteCustomer = customers.get();
	      if (deleteCustomer == null) {
	         throw new NotFoundException("Employee with id-->" + customer_id + " not found.");
	      }
	      repository.deleteById(customer_id);
	      return deleteCustomer;
	   }
	   @Transactional
	   public Customer create(int id, Account account) {
	      Customer customer = repository.findById(id).orElse(null);
	      if (customer == null) {
	         throw new NotFoundException("Customer doesn't exists-->" + id);
	      }
	      Set<Account> accounts = customer.getAccounts();
		 accounts.add(account);
		 return repository.save(customer);
	   }
	   public Account fundTransfer(int fromAccount, int toAccount, double amount) {
	      Optional<Account> fromAcc = repo.findByAccountNumber(fromAccount);
	      if (fromAcc.isEmpty()) {
	         throw new NotFoundException("Account not found with accnumber--# " + fromAccount);
	      }
	      Optional<Account> toAcc = repo.findByAccountNumber(toAccount);
	      // System.out.println(toAcc);
	      if (toAcc.isEmpty()) {
	         throw new NotFoundException("Account not found with accnumber--# " + toAccount);
	      }
	      Account fromAccountDetail = fromAcc.get();
	      double newFromCurrentBalance;
	      double fromAccCurrentbalance = fromAccountDetail.getBalance();
	      if (fromAccCurrentbalance >= amount) {
	         newFromCurrentBalance = fromAccCurrentbalance - amount;
	         fromAccountDetail.setBalance(newFromCurrentBalance);
	      } else {
	         throw new BalanceNotFoundException(
	               "Account has no balance so u cannot transfer amount:balance is" + fromAccCurrentbalance);
	      }
	      System.out.println("FromAccount Details--!" + fromAccountDetail);
	      repo.save(fromAccountDetail);
	      Account toAccountDetail = toAcc.get();
	      double toAccCurrentbalance = toAccountDetail.getBalance();
	      double newToAccCurrentBalance = toAccCurrentbalance + amount;
	      toAccountDetail.setBalance(newToAccCurrentBalance);
	      System.out.println("ToAccount Details--!" + toAccountDetail);
	      repo.save(toAccountDetail);
	      return fromAccountDetail;
	      /*
	       * Optional<Account> fromAcc =repository.findById(fromAccount);
	       * if(fromAcc.isEmpty()) { throw new
	       * NotFoundException("Account not found with accnumber--#"+ fromAccount); }
	       * Account fromAccountDetail=fromAcc.get(); double BalanceincurrentAccount;
	       * double fromAccCurrentbalance = fromAccountDetail.getBalance();
	       * if(fromAccCurrentbalance>=amount) {
	       * 
	       * BalanceincurrentAccount = fromAccCurrentbalance - amount;
	       * fromAccountDetail.setBalance(BalanceincurrentAccount); }else { throw new
	       * BalanceNotFoundException("Account has not Enough  balance  to Transfer ,so u cannot transfer amount: remaining balance is-->"
	       * +fromAccCurrentbalance) ; }
	       * 
	       * System.out.println("***fromAccount Details**"+fromAccountDetail);
	       * repository.save(fromAccountDetail);
	       * 
	       * Optional<Account> toAcc =repository.findById(toAccount); if(toAcc.isEmpty())
	       * { throw new NotFoundException("Account not found with accnumber--#"+
	       * toAccount); } Account toAccountDetail=toAcc.get();
	       * 
	       * double toAccCurrentbalance = toAccountDetail.getBalance(); double
	       * BalanceintoAccount= toAccCurrentbalance + amount;
	       * 
	       * toAccountDetail.setBalance(BalanceintoAccount);
	       * System.out.println("***ToAccount Details**"+toAccountDetail);
	       * repository.save(toAccountDetail);
	       * 
	       * return fromAccountDetail;
	       */
	   }
	   @Transactional
	   public Customer saveCustomer(int id, Account account) {
	      Customer customer = repository.findById(id).orElse(null);
	      if (customer != null) {
	         Set<Account> accounts = customer.getAccounts();
	         accounts.add(account);
	         return repository.save(customer);
	      } else {
	         return customer;
	      }
	   }

}
