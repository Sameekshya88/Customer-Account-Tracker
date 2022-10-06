package com.bank.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.bank.entities.Account;
import com.bank.exception.BalanceNotFoundException;
import com.bank.exception.NotFoundException;
import com.bank.repository.AccountRepository;
@Service
public class AccountService {

	   @Autowired
	   AccountRepository repository;
	   public ResponseEntity<Account> createAccount(Account account) {
	      Account createAccount = repository.save(account);
	      return new ResponseEntity<Account>(createAccount, HttpStatus.CREATED);
	   }
	   public List<Account> getAccounts() {
	      List<Account> accounts = (List<Account>) repository.findAll();
	      return accounts;
	   }
	   public Account getAccount(int accountid) {
	      Optional<Account> accounts = repository.findById(accountid);
	      if (accounts.isEmpty()) {
	         throw new NotFoundException("Account doesn't exists-->" + accountid);
	      }
	      Account account = accounts.get();
	      if (account == null) {
	         throw new NotFoundException("Customer doesn't exists" + accountid);
	      }
	      return account;
	   }
	   public Account updateAccount(int account_id, Account acc) {
	      Optional<Account> accounts = repository.findById(account_id);
	      Account account = accounts.get();
	      if (account == null) {
	         throw new NotFoundException("Account doesn't exists" + account_id);
	      }
	      // account.setBalance(acc.getBalance());
	      account.setType(acc.getType());
	      return repository.save(account);
	   }
	   public Account deleteAccount(int account_id) {
	      Optional<Account> accounts = repository.findById(account_id);
	      Account deleteAccount = accounts.get();
	      if (deleteAccount == null) {
	         throw new NotFoundException("Account with id-->" + account_id + " not found.");
	      }
	      repository.deleteById(account_id);
	      return deleteAccount;
	   }
	   public Account fundTransfer(int fromAccount, int toAccount, double amount) {
	      System.out.println("Acocunt Methid");
	      Optional<Account> fromAcc = repository.findByAccountNumber(fromAccount);
	      if (fromAcc.isEmpty()) {
	         throw new NotFoundException("Account not found with accnumber--# " + fromAccount);
	      }
	      if(amount<=0)
	      {
	         throw new BalanceNotFoundException(
	               "Amount is either negative or Zero #" + amount+" so u cannot transfer the amount to another account");
	      }
	      
	      if(fromAccount==toAccount)
	      {
	         throw new NotFoundException(" Both the Accounts are same--# " + fromAccount+" "+toAccount);
	      }
	      Optional<Account> toAcc = repository.findByAccountNumber(toAccount);
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
	               "Account has not enough balance! so u cannot transfer amount. Balance is--#" + fromAccCurrentbalance);
	      }
	      System.out.println("FromAccount Details--!" + fromAccountDetail);
	      repository.save(fromAccountDetail);
	      Account toAccountDetail = toAcc.get();
	      double toAccCurrentbalance = toAccountDetail.getBalance();
	      double newToAccCurrentBalance = toAccCurrentbalance + amount;
	      toAccountDetail.setBalance(newToAccCurrentBalance);
	      System.out.println("ToAccount Details--!" + toAccountDetail);
	      repository.save(toAccountDetail);
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
	
}
