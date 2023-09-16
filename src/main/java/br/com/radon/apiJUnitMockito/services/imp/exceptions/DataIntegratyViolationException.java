package br.com.radon.apiJUnitMockito.services.imp.exceptions;

public class DataIntegratyViolationException extends RuntimeException{
  
  public DataIntegratyViolationException(String message){
    super(message);
  }
}
