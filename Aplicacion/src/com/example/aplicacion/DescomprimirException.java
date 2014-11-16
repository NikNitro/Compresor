package com.example.aplicacion;

public class DescomprimirException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DescomprimirException() {
		super();
	}
	
	public DescomprimirException(Error e) {
		super(e);
	}
}
