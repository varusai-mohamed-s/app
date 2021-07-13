/**
 * 
 */
package com.example.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.entities.Coin;

/**
 * Repository to handle Coin.
 * 
 * @author Varusai
 *
 */
public interface CoinRepository extends JpaRepository<Coin, Double> {

}
