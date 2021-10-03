package br.com.fatec.apibackend.services;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
  PasswordEncoder passwordEncoder;

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
    user.setSenha(passwordEncoder.encode(user.getSenha()));
    return userRepo.save(user);
  }

  @Transactional
  @PreAuthorize("isAuthenticated()")
  public Usuario editarUsuario(Usuario user) {
    user.setSenha(userRepo.findByDadosEmailEmail(user.getEmail(0)).get(0).getSenha());
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

  @PreAuthorize("hasRole('ADMIN')")
  public void deleteUsuario(long idUser) {
    userRepo.deleteById(idUser);
  }

  public List<Usuario> listaUsuarios() {
    return userRepo.findAll();
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario usuario = userRepo.findByNome(username);
    if (usuario == null) {
      throw new UsernameNotFoundException("Usuário " + username + " não encontrado!");
    }
    return User.builder().username(username).password(usuario.getSenha())
        .authorities(usuario.getAutorizacao().stream().map(Autorizacao::getNome)
            .collect(Collectors.toList()).toArray(new String[usuario.getAutorizacao().size()]))
        .build();
  }
}
