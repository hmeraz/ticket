package com.axity.ticket.handler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.axity.ticket.commons.enums.ErrorCode;
import com.axity.ticket.commons.response.HeaderDto;

/**
 * Validattion Handler class
 * 
 * @author username@axity.com
 */
@ControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler
{
  @Value("${arquetipo.error.1}")
  private String message;

  /**
   * {@inheritDoc}
   */
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid( MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatusCode status, WebRequest request )
  {
    List<String> errors = ex.getBindingResult().getFieldErrors().stream().map( x -> x.getDefaultMessage() )
        .collect( Collectors.toList() );

    var body = new HeaderDto( ErrorCode.REQUIRED_FIELD.getCode(), message );
    body.setDetail( String.join( ",", errors ) );

    return new ResponseEntity<>( body, headers, status );

  }
}
