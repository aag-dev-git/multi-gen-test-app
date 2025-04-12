package com.demo.testApp.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleException(Exception ex) {

		Map<String, Object> body = new HashMap<>();

		body.put("timeStamp", LocalDateTime.now());
		body.put("message", ex.getMessage());
		body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

		return ResponseEntity.internalServerError().body(body);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException ex) {
		
		Map<String, Object> body = new HashMap<String, Object>();

		body.put("timeStamp", LocalDateTime.now());
		body.put("message", ex.getMessage());
		body.put("status", HttpStatus.BAD_REQUEST.value());

		return ResponseEntity.badRequest().body(body);

	}

}
