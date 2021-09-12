package br.com.fatec.apibackend.entities;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonView;
import br.com.fatec.apibackend.views.ViewUsuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "auth")

public class Autorizacao {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonView(ViewUsuario.UsuarioCompletoView.class)
  @Column(name = "auth_id")
  private Long id;

  @JsonView({ViewUsuario.UsuarioView.class, ViewUsuario.UsuarioCompletoView.class})
  @Column(name = "auth_nome")
  private String nome;

  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "autorizacao")
  private Set<Usuario> usuarios;
}
