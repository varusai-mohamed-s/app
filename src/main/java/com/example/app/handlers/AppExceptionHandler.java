/**
 * 
 */
package com.example.app.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.app.exceptions.CoinShortageException;

/**
 * AppExceptionHandler to catch and handle all the exceptions.
 * 
 * @author Varusai
 *
 */
@RestControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(value = { ConstraintViolationException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public List<String> handleValidationExceptions(final ConstraintViolationException ex) {
		List<String> errors = new ArrayList<>();
		ex.getConstraintViolations().forEach(error -> {
			String errorMessage = error.getMessage();
			errors.add(errorMessage);
		});
		return errors;
	}

	@ExceptionHandler(value = { CoinShortageException.class })
	@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
	public String handleCoinShortageException(final CoinShortageException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler(value = { Exception.class })
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleGeneralException(final Exception ex) {
		return ex.getMessage();
	}
}
