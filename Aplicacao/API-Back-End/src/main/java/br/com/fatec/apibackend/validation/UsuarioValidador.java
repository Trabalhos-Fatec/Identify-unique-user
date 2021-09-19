package br.com.fatec.apibackend.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.fatec.apibackend.entities.Usuario;
import br.com.fatec.apibackend.exception.UsuarioException;
import br.com.fatec.apibackend.repository.EmailRepository;
import br.com.fatec.apibackend.utils.ErrorCode;

@Service
public class UsuarioValidador {

  @Autowired
  private EmailRepository repository;

  public void validate(Usuario usuario) {
    Long id = usuario.getId() == null ? 0l : usuario.getId();

    if (repository.findByEmail(usuario.getDados().getEmail().get(0).getEmail()) == null) {
      throw new UsuarioException(ErrorCode.EMAIL_DUPLICATE.getDescription());
    }
  }
}
