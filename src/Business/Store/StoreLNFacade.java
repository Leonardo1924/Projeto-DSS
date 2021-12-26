package Business.Store;

import Business.Store.Funcionario.Funcionario;
import Business.IStoreLN;
import Business.Parser;
import Business.Cliente.Cliente;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class StoreLNFacade implements IStoreLN {
    private boolean funcional;
    private Map<String,Funcionario> credentials;    // username do funcionario, funcionario
    private Map<Integer,Equipamento> equipamentos;   // nif do clinente, equipamento
    private Map<String,Cliente> clientes;           // id do cliente, cliente

    public StoreLNFacade(boolean funcional, Map<String,Funcionario> credentials/*,Map<Integer,Equipamento> equipamentos, Map<String,Cliente> clientes*/){
        this.funcional = funcional;
        this.credentials = credentials.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e-> e.getValue()));
        // this.equipamentos = equipamentos.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e-> e.getValue()));
        // this.clientes = clientes.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e-> e.getValue()));
    }

    public void start() {
    }

    public boolean login(String username,String password) throws IOException {
        Boolean res = false;
        if (this.credentials.containsKey(username)) {
            Funcionario func = this.credentials.get(username);
            if (password.equals(func.getPassword())) {
                res = true;
            }
        }
        return res;
    }

    public void removeCliente(int id){
        this.clientes.remove(id);
    }

    public void registaCliente(String idCliente, String nome, int nif, int telemovel, String mail, String idEquip){
        Equipamento equip = new Equipamento(idEquip);
        Cliente cliente = new Cliente(idCliente,nome,nif,telemovel,mail,equip.clone());
        this.clientes.put(idCliente,cliente.clone());
    }

    public void registaEquip(int nif, String idEquip){
        Equipamento equip = new Equipamento(idEquip);
        this.equipamentos.put(nif,equip.clone());
    }

    public void shutdown() {
        this.funcional = false;
    }
}
