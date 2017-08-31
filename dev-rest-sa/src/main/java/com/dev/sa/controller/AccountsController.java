package com.dev.sa.controller;


import com.dev.sa.entity.Account;
import com.dev.sa.exceptions.AccountNotFoundException;
import com.dev.sa.repository.AccountRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * A RESTFul controller for accessing account information.
 * 
 * @author Paul Chapman
 */
@RestController
public class AccountsController {

	protected Logger logger = Logger.getLogger(AccountsController.class
			.getName());

	@Autowired
	protected AccountRepository accountRepository;



	/**
	 * Fetch an account with the specified account number.n
	 * 
	 * @param accountNumber
	 *            A numeric, 9 digit account number.
	 * @return The account if found.
	 * @throws com.dev.sa.exceptions.AccountNotFoundException
	 *             If the number is not recognised.
	 */
	@RequestMapping("/accounts/{accountNumber}")
	public Account byNumber(@PathVariable("accountNumber") String accountNumber) {


		logger.info("accounts-service byNumber() invoked: " + accountNumber);
		Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
		logger.info("accounts-service byNumber() found: " + account);

		if (account == null)
			throw new AccountNotFoundException(accountNumber);
		else {
			return account;
		}
	}

	/**
	 * Fetch accounts with the specified name. A partial case-insensitive match
	 * is supported. So <code>http://.../accounts/owner/a</code> will find any
	 * accounts with upper or lower case 'a' in their name.
	 * 
	 * @param partialName
	 * @return A non-null, non-empty set of accounts.
	 * @throws AccountNotFoundException
	 *             If there are no matches at all.
	 */
	@RequestMapping("/accounts/owner/{name}")
	public List<Account> byOwner(@PathVariable("name") String partialName) {
		logger.info("accounts-service byOwner() invoked: "
				+ accountRepository.getClass().getName() + " for "
				+ partialName);

		Iterable<Account> accountsItr = accountRepository.findAll();

		List<Account> accounts = Lists.newArrayList(accountsItr);;



		logger.info("accounts-service byOwner() found: " + accounts);

		if (accounts == null || accounts.size() == 0)
			throw new AccountNotFoundException(partialName);
		else {
			return accounts;
		}
	}


	@PostMapping("/Accounts")
	public void save(@RequestBody Account account)
	{
		accountRepository.save(account);
	}
}
