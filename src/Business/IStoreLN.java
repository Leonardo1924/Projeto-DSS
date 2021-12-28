package Business;

import Business.Store.Cliente.IGestClientes;
import Business.Store.Funcionario.IGestFuncionarios;
import Business.Store.Equipamento.IGestEquipamentos;
import Business.Store.Orcamento.IGestOrcamentos;

import java.io.IOException;

public interface IStoreLN {

    IGestClientes getClientesFacade();

    IGestEquipamentos getEquipamentosFacade();

    IGestFuncionarios getFuncionariosFacade();

    IGestOrcamentos getOrcamentosFacade();

    void start();

    boolean login(String user,String password) throws IOException;

    void shutdown();

}
