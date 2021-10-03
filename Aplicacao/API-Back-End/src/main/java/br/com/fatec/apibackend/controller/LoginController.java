package br.com.fatec.apibackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import br.com.fatec.apibackend.entities.Login;
import br.com.fatec.apibackend.entities.Usuario;
import br.com.fatec.apibackend.repository.UserRepository;
import br.com.fatec.apibackend.security.JwtUtils;


@RestController
@RequestMapping(value = "/login")
@CrossOrigin
public class LoginController {

  @Autowired
  private AuthenticationManager authManager;

  @Autowired
  private UserRepository userRepo;

  @PostMapping()
  public Login autenticar(@RequestBody Login login) throws JsonProcessingException {
    Usuario usuario = userRepo.findByDadosEmailEmail(login.getEmail()).get(0);
    login.toEntity(usuario);
    Authentication auth =
        new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
    auth = authManager.authenticate(auth);
    login.setPassword(null);
    login.setToken(JwtUtils.generateToken(auth));
    return login;
  }

}
