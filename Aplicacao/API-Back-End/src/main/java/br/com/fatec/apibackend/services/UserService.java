package br.com.fatec.apibackend.services;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;
import br.com.fatec.apibackend.entities.Usuario;

public interface UserService extends UserDetailsService {

  public Usuario cadastroUsuario(Usuario user);

  public Usuario editarUsuario(Usuario user);

  public void deleteUsuario(long idUser);

  public List<Usuario> listaUsuarios();

  public List<Usuario> ValidaFinger(String Finger);
}
