/**
 * 
 */
package com.example.app.services;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.constants.ValidationErrorConstants;
import com.example.app.dao.CoinRepository;
import com.example.app.entities.Coin;
import com.example.app.exceptions.CoinShortageException;

/**
 * Service implementation.
 * 
 * @author Varusai
 *
 */
@Service
public class ChangeService implements IChangeService {

	@Autowired
	private CoinRepository coinRepository;

	public Map<Double, Integer> getChange(int bill, boolean needMoreCoins) throws CoinShortageException {
		final Comparator<Coin> coinComparator = Comparator.comparingDouble(Coin::getDenominationValue);
		final Map<Double, Integer> output = new LinkedHashMap<>();
		final List<Coin> sortedCoins = coinRepository.findAll().stream()
				.sorted(needMoreCoins ? coinComparator : coinComparator.reversed()).collect(Collectors.toList());

		double finalBill = bill;

		for (Coin coin : sortedCoins) {
			if (finalBill > 0) {
				finalBill = findChangeForBill(finalBill, coin, output);
			} else {
				break;
			}
		}

		// If the final bill is not zero, then coin shortage - can't provide the change.
		if (finalBill != 0) {
			throw new CoinShortageException(ValidationErrorConstants.COIN_SHORTAGE);
		}

		saveCoins(output);

		return output;
	}

	/**
	 * Find change for the given bill and return the remaining bill.
	 * 
	 * @param updatedBill The updated bill.
	 * @param coin        The coin to consider.
	 * @param output      The output containing the change.
	 * @return Returns the updated bill after deducting the current coin.
	 */
	private double findChangeForBill(double updatedBill, Coin coin, Map<Double, Integer> output) {
		final int availableCoinCount = coin.getAvailableCount();

		if (availableCoinCount > 0) {
			int requiredCount = (int) (updatedBill / coin.getDenominationValue());

			if (availableCoinCount >= requiredCount) {
				output.put(coin.getDenominationValue(), requiredCount);
				updatedBill = 0;
			} else {
				output.put(coin.getDenominationValue(), availableCoinCount);
				updatedBill = updatedBill - (coin.getDenominationValue() * availableCoinCount);
			}
		}

		return updatedBill;
	}

	/**
	 * Updates all the coins with latest count.
	 * 
	 * @param coins List of coins
	 */
	@Transactional
	private void saveCoins(Map<Double, Integer> coins) {
		for (Map.Entry<Double, Integer> entry : coins.entrySet()) {
			Coin coin = coinRepository.getById(entry.getKey());
			coinRepository.save(new Coin(entry.getKey(), Math.abs(coin.getAvailableCount() - entry.getValue())));
		}
	}
}