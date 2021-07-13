/**
 * 
 */
package com.example.app.exceptions;

/**
 * CoinShortageException to be thrown when there is a shortage of coins.
 * 
 * @author Varusai
 *
 */
public class CoinShortageException extends RuntimeException {
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -2712159618844084855L;

	/**
	 * @param message
	 */
	public CoinShortageException(String message) {
		super(message);
	}

}
