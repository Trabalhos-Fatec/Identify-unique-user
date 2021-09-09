package br.com.fatec.apibackend.services;

import java.util.HashSet;
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

  @Autowired
  private DadosUsuarioService dadosService;

  @Transactional
  public Usuario cadastroUsuario(Usuario user) {
    HashSet<Autorizacao> hashAuth = new HashSet<Autorizacao>();
    for (Autorizacao auth : user.getAutorizacao()) {
      if (authRepo.findByNome(auth.getNome()) == null) {
        authRepo.save(auth);
      } else {
        hashAuth.add(authRepo.findByNome(auth.getNome()));
      }
    }
    user.setAutorizacao((hashAuth));
    dadosService.cadastroDados(user.getDados());
    System.out.println(user);
    return userRepo.save(user);
  }

  @Transactional
  public Usuario editarUsuario(Usuario user) {
    HashSet<Autorizacao> hashAuth = new HashSet<Autorizacao>();
    for (Autorizacao auth : user.getAutorizacao()) {
      if (authRepo.findByNome(auth.getNome()) == null) {
        authRepo.save(auth);
      } else {
        hashAuth.add(auth);
      }
    }
    user.setAutorizacao((hashAuth));
    dadosService.editarDados(user.getDados());
    return userRepo.save(user);
  }

  public void deleteUsuario(Usuario user) {
    userRepo.delete(user);
  }

  public List<Usuario> listaUsuarios() {
    return userRepo.findAll();
  }
}
