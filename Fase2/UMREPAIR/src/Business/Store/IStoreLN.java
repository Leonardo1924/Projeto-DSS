package Business.Store;

import Business.Store.Cliente.IGestClientes;
import Business.Store.Funcionario.IGestFuncionarios;
import Business.Store.Equipamento.IGestEquipamentos;
import Business.Store.Orcamento.IGestOrcamentos;
import Business.Store.PlanoTrabalho.IGestPlano;
import Business.Store.Servico.IGestServicos;

import java.io.IOException;

public interface IStoreLN {

    IGestClientes getClientesFacade();

    IGestEquipamentos getEquipamentosFacade();

    IGestFuncionarios getFuncionariosFacade();

    IGestOrcamentos getOrcamentosFacade();

    IGestServicos getServicosFacade();

    IGestPlano getPlanosFacade();

    void start();

    boolean login(String user,String password) throws IOException;

    void shutdown();

}
