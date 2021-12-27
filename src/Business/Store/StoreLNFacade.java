package Business.Store;

import Business.Cliente.IGestClientes;
import Business.IStoreLN;
import Business.Parser;
import Business.Store.Funcionario.IGestFuncionarios;
import Business.Store.Equipamento.IGestEquipamentos;

import java.io.IOException;

public class StoreLNFacade implements IStoreLN {
    private boolean funcional;
    private IGestClientes clientesFacade;
    private IGestEquipamentos equipamentosFacade;
    private IGestFuncionarios funcionariosFacade;

    public StoreLNFacade() throws IOException{
        this.funcional = funcional;
        this.clientesFacade = Parser.parseCliente();
        this.equipamentosFacade = Parser.parseEquip();
        this.funcionariosFacade = Parser.parseLogin();
    }

    public void start() {
    }

    public IGestClientes getClientesFacade(){
        return this.clientesFacade;
    }

    public IGestEquipamentos getEquipamentosFacade(){
        return this.equipamentosFacade;
    }

    public IGestFuncionarios getFuncionariosFacade(){
        return this.funcionariosFacade;
    }

    public boolean login(String username,String password) throws IOException {
        return this.funcionariosFacade.login(username,password);
    }

    public void shutdown() {
        this.funcional = false;
    }
}
