package br.com.fatec.apibackend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "email")
public class Email {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonView(ViewUsuario.UsuarioCompletoView.class)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "dados_usuario_id")
  private DadosUsuario dadosUsuario;

  @JsonView({ViewUsuario.UsuarioView.class, ViewUsuario.UsuarioCompletoView.class})
  @Column(name = "email")
  private String email;
}
