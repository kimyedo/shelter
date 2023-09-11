package com.example.demo.exception;

public class DBException extends RuntimeException {
	public DBException() {
		super("@Transactional은 기본적으로 RuntimeException 예외 발생하면 rollback처리함");
	}
}
