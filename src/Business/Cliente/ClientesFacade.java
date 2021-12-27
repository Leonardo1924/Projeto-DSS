package Business.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClientesFacade implements IGestClientes{

    private Map<String,Cliente> clientes;
    private List<String> contactados;
    private List<String> naoContactados;

    public ClientesFacade(Map<String,Cliente> clientes){
        this.clientes = clientes.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e-> e.getValue().clone()));
        this.contactados = null;
        this.naoContactados = new ArrayList<>(clientes.keySet());
    }

    public List<String> getContactados(){
        return new ArrayList<>(this.contactados);
    }

    public List<String> getNaoContactados(){
        return new ArrayList<>(this.naoContactados);
    }

    public Map<String,Cliente> getClientes() {
        return this.clientes.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e-> e.getValue().clone()));
    }

    public void removeCliente(String id){
        this.clientes.remove(id);
    }

    public void registaCliente(String idCliente, String nome, int nif, int telemovel, String mail, String idEquip){
        Cliente cliente = new Cliente(idCliente,nome,nif,telemovel,mail,idEquip);
        this.clientes.put(idCliente,cliente.clone());
    }

    public void consultaClientes(){
        for(Cliente c : this.clientes.values()){
            System.out.println(c.toString());
        }
    }
}
