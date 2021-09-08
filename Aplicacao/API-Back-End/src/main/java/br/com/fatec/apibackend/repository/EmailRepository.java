package br.com.fatec.apibackend.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;
import br.com.fatec.apibackend.entities.Email;

@Repository
public interface EmailRepository extends JpaRepositoryImplementation<Email, Long> {

  public Email findByEmail(String email);

}
