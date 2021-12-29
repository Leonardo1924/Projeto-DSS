package Business;

import Business.Store.Cliente.Cliente;
import Business.Store.Equipamento.Equipamento;
import Business.Store.Funcionario.Funcionario;
import Business.Store.Orcamento.Orcamento;
import Business.Store.PlanoTrabalho.PlanoTrabalho;
import Business.Store.Servico.Servico;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Saver {

    /**
     * Método que grava os dados fornecidos por um utilizador antes do seu logout.
     * @param store Estrutura principal que implementa as funcionalidades do sistema
     */
    public void gravar(IStoreLN store){
        try {
            FileOutputStream fileFunc = new FileOutputStream("input/dadosFuncionarios.txt");
            for (Funcionario func : store.getFuncionariosFacade().getFuncionarios().values()) {
                ObjectOutputStream objOut = new ObjectOutputStream(fileFunc);
                objOut.writeObject(func);
                objOut.close();
            }
            FileOutputStream fileClient = new FileOutputStream("input/dadosClientes.txt");
            for (Cliente cl : store.getClientesFacade().getClientes().values()) {
                ObjectOutputStream objOut = new ObjectOutputStream(fileClient);
                objOut.writeObject(cl);
                objOut.close();
            }
            FileOutputStream fileEquip = new FileOutputStream("input/dadosEquipamentos.txt");
            for (Equipamento eq : store.getEquipamentosFacade().getEquipamentos().values()) {
                ObjectOutputStream objOut = new ObjectOutputStream(fileEquip);
                objOut.writeObject(eq);
                objOut.close();
            }
            FileOutputStream fileOrc = new FileOutputStream("input/dadosOrcamento.txt");
            for (Orcamento orc : store.getOrcamentosFacade().getOrcamentos().values()) {
                ObjectOutputStream objOut = new ObjectOutputStream(fileOrc);
                objOut.writeObject(orc);
                objOut.close();
            }
            FileOutputStream fileServ = new FileOutputStream("input/dadosServico.txt");
            for (Servico s : store.getServicosFacade().getServicos().values()) {
                ObjectOutputStream objOut = new ObjectOutputStream(fileServ);
                objOut.writeObject(s);
                objOut.close();
            }
            FileOutputStream filePlano = new FileOutputStream("input/dadosPlano.txt");
            for (PlanoTrabalho pl : store.getPlanosFacade().getPlanos().values()) {
                ObjectOutputStream objOut = new ObjectOutputStream(filePlano);
                objOut.writeObject(pl);
                objOut.close();
            }
        }catch (Exception e) {
            e.getStackTrace();
        }
    }
}