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
import br.com.fatec.apibackend.validation.UsuarioValidador;

@Service
public class UserServiceImp implements UserService {

  @Autowired
  private UserRepository userRepo;

  @Autowired
  private AuthRepository authRepo;

  @Autowired
  private DadosUsuarioService dadosService;

  @Autowired
  private UsuarioValidador validador;

  @Transactional
  public Usuario cadastroUsuario(Usuario user) {
    HashSet<Autorizacao> hashAuth = new HashSet<Autorizacao>();
    for (Autorizacao auth : user.getAutorizacao()) {
      if (authRepo.findByNome(auth.getNome()) == null) {
        hashAuth.add(authRepo.save(auth));
      } else {
        hashAuth.add(authRepo.findByNome(auth.getNome()));
      }
    }
    user.setAutorizacao(hashAuth);
    dadosService.cadastroDados(user.getDados());
    validador.validate(user);
    return userRepo.save(user);
  }

  @Transactional
  public Usuario editarUsuario(Usuario user) {
    HashSet<Autorizacao> hashAuth = new HashSet<Autorizacao>();
    for (Autorizacao auth : user.getAutorizacao()) {
      if (authRepo.findByNome(auth.getNome()) == null) {
        hashAuth.add(authRepo.save(auth));
      } else {
        hashAuth.add(authRepo.findByNome(auth.getNome()));
      }
    }
    user.setAutorizacao(hashAuth);
    dadosService.cadastroDados(user.getDados());

    return userRepo.save(user);
  }

  public void deleteUsuario(long idUser) {
    userRepo.deleteById(idUser);
  }

  public List<Usuario> listaUsuarios() {
    return userRepo.findAll();
  }

  public Usuario doLogin(String email, String pass) {
    Usuario user = userRepo.findByDadosEmailEmail(email).get(0);
    if (pass.equals(user.getSenha())) {
      return user;
    } else {
      return null;
    }

  }
}
