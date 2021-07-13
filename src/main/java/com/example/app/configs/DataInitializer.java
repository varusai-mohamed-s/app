/**
 * 
 */
package com.example.app.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.app.dao.CoinRepository;
import com.example.app.entities.Coin;

/**
 * DataInitializer initialized some Coins.
 * 
 * @author Varusai
 *
 */
@Component
public class DataInitializer implements CommandLineRunner {
	@Autowired
	private CoinRepository repository;

	@Override
	public void run(String... args) throws Exception {
		// Initialize in-memory database with default coins.
		repository.save(new Coin(0.01, 100));
		repository.save(new Coin(0.05, 100));
		repository.save(new Coin(0.10, 100));
		repository.save(new Coin(0.25, 100));
	}

}
