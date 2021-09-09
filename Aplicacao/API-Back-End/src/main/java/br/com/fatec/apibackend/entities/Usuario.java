package br.com.fatec.apibackend.entities;

import java.util.Set;
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
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "usuario")
public class Usuario {

  @Id
  @JsonView(ViewUsuario.UsuarioCompletoView.class)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "usuario_id")
  private Long id;

  @JsonView({ViewUsuario.UsuarioView.class, ViewUsuario.UsuarioCompletoView.class})
  @Column(name = "usuario_nome", length = 150)
  private String nome;

  @Column(name = "usuario_senha")
  private String senha;

  @JsonView({ViewUsuario.UsuarioView.class, ViewUsuario.UsuarioCompletoView.class})
  @Column(name = "usuario_atividade")
  private boolean atividade;

  @JsonView(ViewUsuario.UsuarioCompletoView.class)
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "usuario_autorizacao", joinColumns = {@JoinColumn(name = "usuario_id")},
      inverseJoinColumns = {@JoinColumn(name = "auth_id")})
  private Set<Autorizacao> autorizacao;

  @JsonView(ViewUsuario.UsuarioCompletoView.class)
  @OneToOne(fetch = FetchType.LAZY, mappedBy = "usuario")
  private DadosUsuario dados;

}
