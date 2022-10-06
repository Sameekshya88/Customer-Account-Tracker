package com.bank.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Account {
	
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   int accountNumber;
	   @Enumerated(EnumType.STRING)
	   Account_type type;
	   double balance;
	   public int getAccountNumber() {
	      return accountNumber;
	   }
	   public Account() {
	      super();
	      // TODO Auto-generated constructor stub
	   }
	   public Account(int accountNumber, Account_type type, double balance) {
	      super();
	      this.accountNumber = accountNumber;
	      this.type = type;
	      this.balance = balance;
	   }
	   public Account(Account_type type, double balance) {
	      // TODO Auto-generated constructor stub
	      super();
	      this.type = type;
	      this.balance = balance;
	   }
	   public void setAccountNumber(int accountNumber) {
	      this.accountNumber = accountNumber;
	   }
	   public Account_type getType() {
	      return type;
	   }
	   public void setType(Account_type type) {
	      this.type = type;
	   }
	   public double getBalance() {
	      return balance;
	   }
	   public void setBalance(double balance) {
	      this.balance = balance;
	   }
	   @Override
	   public String toString() {
	      return "Account [accountNumber=" + accountNumber + ", accountType=" + type + ", balance=" + balance + "]";
	   }
	

}
