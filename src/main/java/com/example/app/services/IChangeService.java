/**
 * 
 */
package com.example.app.services;

import java.util.Map;

import com.example.app.exceptions.CoinShortageException;

/**
 * @author Varusai
 *
 */
public interface IChangeService {
	Map<Double, Integer> getChange(int bill, boolean needMoreCoins) throws CoinShortageException;
}
