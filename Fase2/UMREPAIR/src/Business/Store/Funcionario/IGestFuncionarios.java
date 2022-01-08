package Business.Store.Funcionario;

import Business.Store.Equipamento.Equipamento;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IGestFuncionarios {

    String getUserAtual();

    Map<String,Funcionario> getFuncionarios();

    boolean login(String username,String password) throws IOException;

    String contactaCliente(String idCliente, List<String> naoContactados, List<String> contactados);

    String getTipoFuncionario(String username);

    boolean validateFuncionario(String idTecnico);

    void equipRecebidos(String equip);

    void equipDevolvidos(Equipamento equip);

    Map<String,List<String>> getRececao();

    Map<String,List<String>> getDevolucao();

    Map<String,List<String>> constroiDevolucao(Map<String,Funcionario> credentials);

    Map<String,List<String>> constroiRececao(Map<String,Funcionario> credentials);
}
