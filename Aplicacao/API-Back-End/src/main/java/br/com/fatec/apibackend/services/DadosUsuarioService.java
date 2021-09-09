package br.com.fatec.apibackend.services;

import java.util.List;
import br.com.fatec.apibackend.entities.DadosUsuario;

public interface DadosUsuarioService {

  public DadosUsuario cadastroDados(DadosUsuario dados);

  public DadosUsuario editarDados(DadosUsuario dados);

  public void deleteDados(DadosUsuario dados);

  public List<DadosUsuario> listaDados();

}
