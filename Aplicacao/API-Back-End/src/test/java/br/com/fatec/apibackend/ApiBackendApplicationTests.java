package br.com.fatec.apibackend;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.ArrayList;
import java.util.HashSet;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import br.com.fatec.apibackend.entities.Autorizacao;
import br.com.fatec.apibackend.entities.DadosUsuario;
import br.com.fatec.apibackend.entities.Email;
import br.com.fatec.apibackend.entities.Telefone;
import br.com.fatec.apibackend.entities.Usuario;
import br.com.fatec.apibackend.repository.AuthRepository;
import br.com.fatec.apibackend.repository.DadosRepository;
import br.com.fatec.apibackend.repository.EmailRepository;
import br.com.fatec.apibackend.repository.TelefoneRepository;
import br.com.fatec.apibackend.repository.UserRepository;
import br.com.fatec.apibackend.services.UserService;

@SpringBootTest
@Transactional
@Rollback
class ApiBackendApplicationTests {

  @Autowired
  private AuthRepository authRepo;

  @Autowired
  private UserRepository userRepo;

  @Autowired
  private TelefoneRepository tellRepo;

  @Autowired
  private EmailRepository emailRepo;

  @Autowired
  private UserService userServ;

  @Autowired
  private DadosRepository dadosRepo;

  @Test
  void cadastroAuth() {
    Autorizacao auth = new Autorizacao();
    auth.setNome("teste");
    authRepo.save(auth);

    assertFalse(authRepo.findByNome("teste").getNome().isEmpty());
  }

  @Test
  void cadastroUser() {
    cadastroDefault();

    assertNotNull(userRepo.findByNome("Carinha"));

  }

  @Test
  void pesquisaTest() {
    cadastroDefault();
    assertNotNull(userRepo.findByDadosEmailEmail("SouSpam@uol.com.br"));
  }

  @Test
  void pesquisaAvancadaTest() {
    cadastroDefault();
    assertNotNull(userRepo.findByAutorizacaoNomeAndAtividade("teste", true));
  }

  void cadastroDefault() {
    Autorizacao auth = new Autorizacao();
    auth.setNome("teste");

    Email email = new Email();
    email.setEmail("SouSpam@uol.com.br");

    Telefone tel = new Telefone();
    tel.setTelefone("(12)98847-1893");

    DadosUsuario dados = new DadosUsuario();

    dados.setEmail(new ArrayList<Email>());
    dados.getEmail().add(emailRepo.save(email));

    dados.setTelefone(new ArrayList<Telefone>());
    dados.getTelefone().add(tellRepo.save(tel));

    Usuario user = new Usuario();
    user.setNome("Carinha");
    user.setAtividade(true);
    user.setSenha("senha");
    user.setAutorizacao(new HashSet<Autorizacao>());
    user.getAutorizacao().add(auth);
    user.setDados(dadosRepo.save(dados));

    userServ.cadastroUsuario(user);
  }
}
