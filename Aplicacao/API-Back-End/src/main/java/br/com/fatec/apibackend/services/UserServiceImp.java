package br.com.fatec.apibackend.services;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.fatec.apibackend.entities.Autorizacao;
import br.com.fatec.apibackend.entities.Usuario;
import br.com.fatec.apibackend.repository.AuthRepository;
import br.com.fatec.apibackend.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

  @Autowired
  private UserRepository userRepo;

  @Autowired
  private AuthRepository authRepo;

  @Transactional
  public Usuario cadastroUsuario(Usuario user) {
    for (Autorizacao auth : user.getAutorizacao()) {
      if (authRepo.findByNome(auth.getNome()) == null) {
        authRepo.save(auth);
      }
    }
    return userRepo.save(user);
  }

  public Usuario editarUsuario(Usuario user) {
    for (Autorizacao auth : user.getAutorizacao()) {
      if (authRepo.findByNome(auth.getNome()) == null) {
        authRepo.save(auth);
      }
    }
    return userRepo.save(user);
  }

  public void deleteUsuario(Usuario user) {
    userRepo.delete(user);
  }

  public List<Usuario> listaUsuarios() {
    return userRepo.findAll();
  }
}
