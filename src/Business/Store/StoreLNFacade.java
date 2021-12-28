package Business.Store;

import Business.Store.Cliente.IGestClientes;
import Business.IStoreLN;
import Business.Parser;
import Business.Store.Funcionario.IGestFuncionarios;
import Business.Store.Equipamento.IGestEquipamentos;
import Business.Store.Orcamento.IGestOrcamentos;

import java.io.IOException;

public class StoreLNFacade implements IStoreLN {
    private boolean funcional;
    private IGestClientes clientesFacade;
    private IGestEquipamentos equipamentosFacade;
    private IGestFuncionarios funcionariosFacade;
    private IGestOrcamentos orcamentosFacade;

    public StoreLNFacade() throws IOException{
        this.funcional = funcional;
        this.clientesFacade = Parser.parseCliente();
        this.equipamentosFacade = Parser.parseEquip();
        this.funcionariosFacade = Parser.parseLogin();
        this.orcamentosFacade = Parser.parseOrcamento();
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

    public IGestOrcamentos getOrcamentosFacade(){
        return this.orcamentosFacade;
    }

    public boolean login(String username, String password) throws IOException {
        return this.funcionariosFacade.login(username,password);
    }

    public void shutdown() {
        this.funcional = false;
    }
}
