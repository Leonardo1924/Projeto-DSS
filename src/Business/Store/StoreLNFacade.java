package Business.Store;

import Business.Store.Funcionario.Funcionario;
import Business.IStoreLN;
import Business.Parser;
import Business.Cliente.Cliente;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

public class StoreLNFacade implements IStoreLN {
    private boolean funcional;
    private Map<String,Funcionario> credentials;     // username do funcionario, funcionario
    private Map<Integer,Equipamento> equipamentos;   // nif do clinente, equipamento
    private Map<String,Cliente> clientes;            // id do cliente, cliente
    private List<String> contactados;
    private List<String> naoContactados;

    public StoreLNFacade(boolean funcional, Map<String,Funcionario> credentials,Map<String,Cliente> clientes/*,Map<Integer,Equipamento> equipamentos*/){
        this.funcional = funcional;
        this.credentials = credentials.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e-> e.getValue().clone()));
        this.clientes = clientes.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e-> e.getValue().clone()));
        //this.equipamentos = equipamentos.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e-> e.getValue().clone()));
        this.contactados = null;
        this.naoContactados = new ArrayList<String>(clientes.keySet());
    }
    public Map<String,Cliente> getClientes() {
        return this.clientes.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e-> e.getValue().clone()));
    }

    public List<String> getContactados(){
        return this.contactados.stream().collect(Collectors.toList());
    }

    public List<String> getNaoContactados(){
        return this.naoContactados.stream().collect(Collectors.toList());
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

    public void removeCliente(String id){
        this.clientes.remove(id);
    }

    public void registaCliente(String idCliente, String nome, int nif, int telemovel, String mail, String idEquip){
        Cliente cliente = new Cliente(idCliente,nome,nif,telemovel,mail,idEquip);
        this.clientes.put(idCliente,cliente.clone());
    }

    public void registaEquip(int nif, String idEquip){
        Equipamento equip = new Equipamento(idEquip);
        this.equipamentos.put(nif,equip.clone());
    }

    public void consultaClientes(){
        Map<String,Cliente> clientes = this.model.getClientes();
        for(Cliente c : clientes.values()){
            System.out.println(c.toString());
        }
    }

    public void shutdown() {
        this.funcional = false;
    }
}
