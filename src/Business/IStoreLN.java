package Business;

import Business.Cliente.IGestClientes;
import Business.Store.Funcionario.IGestFuncionarios;
import Business.Store.Equipamento.IGestEquipamentos;

import java.io.IOException;

public interface IStoreLN {

    IGestClientes getClientesFacade();

    IGestEquipamentos getEquipamentosFacade();

    IGestFuncionarios getFuncionariosFacade();

    void start();

    boolean login(String user,String password) throws IOException;

    void shutdown();
}
