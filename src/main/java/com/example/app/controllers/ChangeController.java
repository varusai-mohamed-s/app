/**
 * 
 */
package com.example.app.controllers;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.services.IChangeService;

/**
 * Controller class to handle the requests related to change(bill).
 * 
 * @author Varusai
 *
 */
@RestController
@Validated
public class ChangeController {

	@Autowired
	private IChangeService changeService;

	@GetMapping("/change/{bill}")
	public ResponseEntity<Object> getChange(@PathVariable("bill") @Min(value = 1, message = "{bill.invalid}") int bill,
			@RequestParam(required = false, defaultValue = "false", value = "needMoreCoins") boolean needMoreCoins) {
		return ResponseEntity.ok(changeService.getChange(bill, needMoreCoins));
	}
}
