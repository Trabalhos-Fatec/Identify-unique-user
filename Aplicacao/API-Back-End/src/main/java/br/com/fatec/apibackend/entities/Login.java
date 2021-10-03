package br.com.fatec.apibackend.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Login {

  private String email;

  private String username;

  private String password;

  private String autorizacao;

  private String token;

  public void toEntity(Usuario usuario) {
    this.username = usuario.getNome();
  }

}
