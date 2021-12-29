package Business.Store;

import Business.Store.Cliente.IGestClientes;
import Business.InPutOutPut.Parser;
import Business.Store.Funcionario.IGestFuncionarios;
import Business.Store.Equipamento.IGestEquipamentos;
import Business.Store.Orcamento.IGestOrcamentos;
import Business.Store.PlanoTrabalho.IGestPlano;
import Business.Store.Servico.IGestServicos;

import java.io.IOException;

public class StoreLNFacade implements IStoreLN {
    private boolean funcional;
    private IGestClientes clientesFacade;
    private IGestEquipamentos equipamentosFacade;
    private IGestFuncionarios funcionariosFacade;
    private IGestOrcamentos orcamentosFacade;
    private IGestServicos servicosFacade;
    private IGestPlano planosFacade;

    public StoreLNFacade() throws IOException{
        this.funcional = funcional;
        this.clientesFacade = Parser.parseCliente();
        this.equipamentosFacade = Parser.parseEquip();
        this.funcionariosFacade = Parser.parseLogin();
        this.orcamentosFacade = Parser.parseOrcamento();
        this.servicosFacade = Parser.parseServico();
        this.planosFacade = Parser.parsePlano();
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

    public IGestServicos getServicosFacade() {
        return servicosFacade;
    }

    public IGestPlano getPlanosFacade() {
        return planosFacade;
    }

    public boolean login(String username, String password) throws IOException {
        return this.funcionariosFacade.login(username,password);
    }

    public void shutdown() {
        this.funcional = false;
    }
}
