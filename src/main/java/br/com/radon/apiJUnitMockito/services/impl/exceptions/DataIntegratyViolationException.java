package br.com.radon.apiJUnitMockito.services.impl.exceptions;

public class DataIntegratyViolationException extends RuntimeException{
  
  public DataIntegratyViolationException(String message){
    super(message);
  }
}
