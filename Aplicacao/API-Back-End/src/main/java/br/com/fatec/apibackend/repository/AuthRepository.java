package br.com.fatec.apibackend.repository;


import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;
import br.com.fatec.apibackend.entities.Autorizacao;

@Repository

// query email de um usuario
public interface AuthRepository extends JpaRepositoryImplementation<Autorizacao, Long> {

  public Autorizacao findByNome(String nome);

}
