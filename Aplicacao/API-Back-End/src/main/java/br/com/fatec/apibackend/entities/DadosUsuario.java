package br.com.fatec.apibackend.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "dados_usuario")
public class DadosUsuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonView(ViewUsuario.UsuarioCompletoView.class)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "dados")
  private Usuario usuario;

  @JsonView({ViewUsuario.UsuarioView.class, ViewUsuario.UsuarioCompletoView.class})
  @OneToMany(mappedBy = "dadosUsuario", orphanRemoval = true)
  private List<Email> email;

  @JsonView({ViewUsuario.UsuarioView.class, ViewUsuario.UsuarioCompletoView.class})
  @OneToMany(mappedBy = "dadosUsuario", orphanRemoval = true)
  private List<Telefone> telefone;

}
