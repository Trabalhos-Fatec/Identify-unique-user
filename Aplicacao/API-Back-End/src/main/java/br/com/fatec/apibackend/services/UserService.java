package br.com.fatec.apibackend.services;

import java.util.List;
import br.com.fatec.apibackend.entities.Usuario;

public interface UserService {

  public Usuario cadastroUsuario(Usuario user);

  public Usuario editarUsuario(Usuario user);

  public void deleteUsuario(Usuario user);

  public List<Usuario> listaUsuarios();

}
