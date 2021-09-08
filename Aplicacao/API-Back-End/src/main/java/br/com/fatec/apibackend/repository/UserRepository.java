package br.com.fatec.apibackend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;
import br.com.fatec.apibackend.entities.Usuario;

@Repository
public interface UserRepository extends JpaRepositoryImplementation<Usuario, Long> {

  public Usuario findByNome(String nome);

  public List<Usuario> findByAutorizacaoNome(String nomeAuth);

  public List<Usuario> findByDadosEmailEmail(String email);

  public List<Usuario> findByAutorizacaoNomeAndAtividade(String nomeAuth, boolean atividade);

}
