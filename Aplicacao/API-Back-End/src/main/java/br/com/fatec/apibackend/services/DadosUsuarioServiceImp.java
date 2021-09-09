package br.com.fatec.apibackend.services;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.fatec.apibackend.entities.DadosUsuario;
import br.com.fatec.apibackend.entities.Email;
import br.com.fatec.apibackend.entities.Telefone;
import br.com.fatec.apibackend.repository.DadosRepository;
import br.com.fatec.apibackend.repository.EmailRepository;
import br.com.fatec.apibackend.repository.TelefoneRepository;

@Service
public class DadosUsuarioServiceImp implements DadosUsuarioService {

  @Autowired
  private DadosRepository dadosRepo;

  @Autowired
  private EmailRepository emailRepo;

  @Autowired
  private TelefoneRepository telRepo;

  @Transactional
  public DadosUsuario cadastroDados(DadosUsuario dados) {
    ArrayList<Email> listEmail = new ArrayList<Email>();
    ArrayList<Telefone> listTelefone = new ArrayList<Telefone>();

    for (Email email : dados.getEmail()) {
      if (emailRepo.findByEmail(email.getEmail()) == null) {
        listEmail.add(emailRepo.save(email));
      } else {
        listEmail.add(email);
      }
    }

    for (Telefone telefone : dados.getTelefone()) {
      if (telRepo.findByTelefone(telefone.getTelefone()) == null) {
        telRepo.save(telefone);
        listTelefone.add(telRepo.save(telefone));
      } else {
        listTelefone.add(telefone);
      }
    }
    dados.setEmail(listEmail);
    dados.setTelefone(listTelefone);

    return dadosRepo.save(dados);
  }

  @Transactional
  public DadosUsuario editarDados(DadosUsuario dados) {
    ArrayList<Email> listEmail = new ArrayList<Email>();
    ArrayList<Telefone> listTelefone = new ArrayList<Telefone>();

    for (Email email : dados.getEmail()) {
      if (emailRepo.findByEmail(email.getEmail()) == null) {
        listEmail.add(emailRepo.save(email));
      } else {
        listEmail.add(email);
      }
    }

    for (Telefone telefone : dados.getTelefone()) {
      if (telRepo.findByTelefone(telefone.getTelefone()) == null) {
        telRepo.save(telefone);
        listTelefone.add(telRepo.save(telefone));
      } else {
        listTelefone.add(telefone);
      }
    }
    dados.setEmail(listEmail);
    dados.setTelefone(listTelefone);

    return dadosRepo.save(dados);
  }

  public void deleteDados(DadosUsuario dados) {
    dadosRepo.delete(dados);
  }

  public List<DadosUsuario> listaDados() {
    return dadosRepo.findAll();
  }

}
