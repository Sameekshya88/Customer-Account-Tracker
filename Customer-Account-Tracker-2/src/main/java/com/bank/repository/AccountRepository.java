package com.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bank.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository <Account,Integer>{
	
	@Query(value= "select * from Account where Account.account_number=?1",nativeQuery=true)
	public Optional<Account> findByAccountNumber(int account_number);
	

}
