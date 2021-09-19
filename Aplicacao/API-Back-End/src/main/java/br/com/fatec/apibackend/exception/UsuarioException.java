package br.com.fatec.apibackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsuarioException extends RuntimeException {

  public UsuarioException(String message) {
    super(message);
  }
}

