package br.com.fatec.apibackend.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;
import br.com.fatec.apibackend.entities.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepositoryImplementation<Telefone, Long> {

  public Telefone findByTelefone(String telefone);

}
