package com.bank.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.bank.entities.Account;
import com.bank.entities.Fundtransfer;
import com.bank.service.AccountService;
//@restcontroller is an inbuild mapping method like annotaion
//responsible for creating restful controlllers
//Controllers are using in spring mvc 
//unlike Rest Controllers are usrd in restful web services
//The web services that follows the REST architectural style is called RESTful Web Services. 
//It differentiates between the computer system and web services.
//The REST architectural style describes the six barriers.
@RestController
public class AccountController {
	
	
	   @Autowired
	   AccountService accountservice;
	   // 1..create operation
	   // @Requestbody---> This is used to convert the body of the HTTP request to the
	   // java class object
	   // with the aid of selected HTTP message converter.
	   // This annotation will be used in the method parameter and the body of the http
	   // request
	   // will be mapped to that method parameter.-->
	   // @PostMapping The POST HTTP method is used to create a resource
	   // and @PostMapping annotation for mapping HTTP POST requests onto specific
	   // handlermethods.
	   // Specifically, @PostMapping is a composed annotation that acts as a shortcut
	   // for @RequestMapping (method = RequestMethod.POST).
	   @PostMapping("/api/accounts")
	   public ResponseEntity<Account> createAccount(@RequestBody Account account) {
	      return accountservice.createAccount(account);
	   }
	   // 2..read opertion for all employess
	   // @GetMapping annotation maps HTTP GET requests onto specific handler methods
	   // It is a composed annotation that acts as a shortcut for @RequestMapping
	   // (method = RequestMethod.GET) .
	   // The following application uses @GetMapping to map two request paths onto
	   // handler methods.
	   @GetMapping("/api/accounts")
	   public List<Account> getAccounts() {
	      List<Account> accounts = accountservice.getAccounts();
	      return accounts;
	   }
	   // 3..read operation for specific or particular employee
	   // instead of using getmapping we can use requestmapping method..
	   // because we need particular employee
	   // @Pathvariable refers to local variable
	   // finding specific employee_id when user enter their id
	   // so we need to iterate over the list
	   // employees refers to Arraylist value
	   // emp stores employee values
	   // initializing tepemp==null value because we need to store it..
	   @RequestMapping(value = "/api/accounts/{account_id}", method = RequestMethod.GET)
	   public Account getAccount(@PathVariable int account_id) {// @Pathvariable refers to local variable
	      Account account = accountservice.getAccount(account_id);
	      return account;
	   }
	   // 2..read opertion for employee
	   // The PUT HTTP method is used to update the resource and
	   // @PutMapping annotation for mapping HTTP PUT requests onto specific handler
	   // methods.
	   // Specifically, @PutMapping is a composed annotation that acts as a shortcut
	   // for @RequestMapping(method = RequestMethod.PUT).
	   // update method also need employee object @RequestBody
	   @PutMapping("/api/accounts/{account_id}")
	   public Account updateAccount(@PathVariable int account_id, @RequestBody Account account) {
	      Account accountUpdate = accountservice.updateAccount(account_id, account);
	      return accountUpdate;
	   }
	   // Delete operation
	   // The DELETE HTTP method is used to delete the resource
	   // and @DeleteMapping annotation for mapping HTTP DELETE requests onto specific
	   // handler methods.
	   // Specifically, @DeleteMapping is a composed annotation that acts as a shortcut
	   // for @RequestMapping(method = RequestMethod.DELETE).
	   @DeleteMapping("/api/accounts/{account_id}")
	   public Account deleteAccount(@PathVariable int account_id) {
	      Account accountDelete = accountservice.deleteAccount(account_id);
	      return accountDelete;
	   }
	   @PostMapping("/api/accounts/transferFund/{fromAccNumber}/{toAccNumber}/{amount}")
	   public Account fundsTransfer(@PathVariable int fromAccNumber, @PathVariable int toAccNumber,
	         @PathVariable double amount) {
	      Account a = accountservice.fundTransfer(fromAccNumber, toAccNumber, amount);
	      return a;
	   }
	   
	   @PutMapping("/trans")
	   public Account fund(@RequestBody Fundtransfer transfer)
	   {
	      Account acc = accountservice.fundTransfer(transfer.getFrom(),transfer.getTo(),transfer.getAmount());
	      return acc;
	   }
	   /*
	    * @GetMapping - shortcut for @RequestMapping(method = RequestMethod.GET)
	    * 
	    * @PostMapping - shortcut for @RequestMapping(method = RequestMethod.POST)
	    * 
	    * @PutMapping - shortcut for @RequestMapping(method = RequestMethod.PUT)
	    * 
	    * @DeleteMapping - shortcut for @RequestMapping(method =RequestMethod.DELETE)
	    * 
	    * @PatchMapping - shortcut for @RequestMapping(method = RequestMethod.PATCH)
	    * 
	    * And also by using above request method we can access this based on the
	    * request from the Postman if it was put it used that request. as like
	    * remaining all..
	    */
	
}
