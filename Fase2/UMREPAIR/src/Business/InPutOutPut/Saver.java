package Business.InPutOutPut;

import Business.Store.Cliente.Cliente;
import Business.Store.Equipamento.Equipamento;
import Business.Store.Funcionario.Funcionario;
import Business.Store.IStoreLN;
import Business.Store.Orcamento.Orcamento;
import Business.Store.PlanoTrabalho.PlanoTrabalho;
import Business.Store.Servico.Servico;

import java.io.*;
import java.util.Map;

public class Saver {

    /**
     * Método que grava os dados fornecidos por um utilizador antes do seu logout.
     * @param store Estrutura principal que implementa as funcionalidades do sistema
    */
    public static void gravar(IStoreLN store){
            Map<String,Funcionario> func = store.getFuncionariosFacade().getFuncionarios();
            Map<String,Cliente> clientes = store.getClientesFacade().getClientes();
            Map<Integer,Equipamento> equip = store.getEquipamentosFacade().getEquipamentos();
            Map<Integer,Orcamento> orc = store.getOrcamentosFacade().getOrcamentos();
            Map<Integer,Servico> serv = store.getServicosFacade().getServicos();
            Map<Integer,PlanoTrabalho> planos = store.getPlanosFacade().getPlanos();

            try{
                File fileFunc = new File("input/dadosFuncionarios.txt");
                if(!(fileFunc.exists())) fileFunc.createNewFile();
                PrintWriter printFunc = new PrintWriter(fileFunc);
                for(Funcionario funcionario : func.values()){
                    funcionario.gravar(printFunc);
                }
                printFunc.close();

                File fileClient = new File("input/dadosClientes.txt");
                if(!(fileClient.exists())) fileClient.createNewFile();
                PrintWriter printClient = new PrintWriter(fileClient);
                for(Cliente cl : clientes.values()){
                    cl.gravar(printClient);
                }
                printClient.close();

                File fileEquip = new File("input/dadosEquipamentos.txt");
                if(!(fileEquip.exists())) fileEquip.createNewFile();
                PrintWriter printEquip = new PrintWriter(fileEquip);
                for(Map.Entry<Integer,Equipamento> eq: equip.entrySet()){
                    eq.getValue().gravar(eq.getKey(),printEquip);
                }
                printEquip.close();

                File fileOrc = new File("input/dadosOrcamento.txt");
                if(!(fileOrc.exists())) fileOrc.createNewFile();
                PrintWriter printOrc = new PrintWriter(fileOrc);
                for(Orcamento o : orc.values()){
                    o.gravar(printOrc);
                }
                printOrc.close();

                File fileServ = new File("input/dadosServico.txt");
                if(!(fileServ.exists())) fileServ.createNewFile();
                PrintWriter printServ = new PrintWriter(fileServ);
                for(Servico s : serv.values()){
                    s.gravar(printServ);
                }
                printServ.close();

                File filePlano = new File("input/dadosPlano.txt");
                if(!(filePlano.exists())) filePlano.createNewFile();
                PrintWriter printPlano = new PrintWriter(filePlano);
                for(PlanoTrabalho pl : planos.values()){
                    pl.gravar(printPlano);
                }
                printPlano.close();
            }
            catch(IOException e) { e.printStackTrace(); }
        }
}