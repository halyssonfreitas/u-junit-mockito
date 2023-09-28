package br.com.radon.apiJUnitMockito.services.impl.exceptions;

public class DataIntegrityViolationException extends RuntimeException{
  
  public DataIntegrityViolationException(String message){
    super(message);
  }
}
