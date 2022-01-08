
package Business.Store.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClientesFacade implements IGestClientes{

    private Map<String,Cliente> clientes;
    private List<String> contactados;
    private List<String> naoContactados;

    public ClientesFacade(Map<String,Cliente> clientes, List<String> contactados, List<String> naoContactados){
        this.clientes = clientes.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e-> e.getValue().clone()));
        this.contactados = new ArrayList<>(contactados);
        this.naoContactados = new ArrayList<>(naoContactados);
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

    public boolean removeCliente(String id){
        if(this.clientes.containsKey(id)) {
            this.clientes.remove(id);
            return true;
        }
        else return false;
    }

    public void registaCliente(String idCliente, String nome, int nif, int telemovel, String mail, String idEquip){
        Cliente cliente = new Cliente(idCliente, nome, nif, telemovel, mail, idEquip);
        this.clientes.put(idCliente, cliente.clone());
    }

    public void consultaClientes(){
        for(Cliente c : this.clientes.values()){
            System.out.println(c.toString());
        }
    }

    public boolean existeCliente(String id){
        return this.clientes.containsKey(id);
    }

    @Override
    public int getClienteEQ(String eqID) {
        for(Cliente cl : this.clientes.values())
            if (cl.getEquipamento().equals(eqID)) return cl.getNif();
        return 0;
    }
}