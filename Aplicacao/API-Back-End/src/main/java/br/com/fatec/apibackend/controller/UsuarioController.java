package br.com.fatec.apibackend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.fatec.apibackend.entities.Usuario;
import br.com.fatec.apibackend.services.UserService;

@RestController
@CrossOrigin
@RequestMapping(value = "/usuario")
public class UsuarioController {

  @Autowired
  public UserService userServ;

  @PostMapping()
  public ResponseEntity<Usuario> cadastrarUser(@RequestBody Usuario usuario) {
    return ResponseEntity.ok(userServ.cadastroUsuario(usuario));
  }

  @PutMapping()
  public ResponseEntity<Usuario> editarUser(@RequestBody Usuario usuario) {
    return ResponseEntity.ok(userServ.editarUsuario(usuario));
  }

  @DeleteMapping()
  public ResponseEntity<Usuario> deletaUser(@RequestBody Usuario usuario) {
    userServ.deleteUsuario(usuario);
    return ResponseEntity.ok().build();
  }

  @GetMapping()
  public ResponseEntity<List<Usuario>> getLista() {
    return ResponseEntity.ok().body(userServ.listaUsuarios());
  }
}
