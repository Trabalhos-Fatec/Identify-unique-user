package br.com.fatec.apibackend.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonView;
import br.com.fatec.apibackend.views.ViewUsuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {

  @Id
  @JsonView(ViewUsuario.UsuarioCompletoView.class)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonView({ViewUsuario.UsuarioView.class, ViewUsuario.UsuarioCompletoView.class})
  @Column(name = "usuario_nome", length = 150)
  private String nome;

  @Column(name = "usuario_senha")
  private String senha;

  @JsonView(ViewUsuario.UsuarioCompletoView.class)
  @Column(name = "usuario_fingerprint")
  private String fingerprint;

  @JsonView(ViewUsuario.UsuarioCompletoView.class)
  @Column(name = "usuario_components")
  private String components;

  @JsonView({ViewUsuario.UsuarioView.class, ViewUsuario.UsuarioCompletoView.class})
  @Column(name = "usuario_atividade")
  private boolean atividade;

  @JsonView({ViewUsuario.UsuarioView.class, ViewUsuario.UsuarioCompletoView.class})
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "usuario_autorizacao", joinColumns = {@JoinColumn(name = "usuario_id")},
      inverseJoinColumns = {@JoinColumn(name = "auth_id")})
  private Set<Autorizacao> autorizacao = new HashSet<>();

  @JsonView({ViewUsuario.UsuarioView.class, ViewUsuario.UsuarioCompletoView.class})
  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "dados_usuario_id")
  private DadosUsuario dados;

}
