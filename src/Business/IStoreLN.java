package Business;

import Business.Cliente.Cliente;
import Business.Store.Equipamento;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IStoreLN {

    Map<String, Cliente> getClientes();

    Map<Integer, Equipamento> getEquipamentos();

    List<String> getContactados();

    List<String> getNaoContactados();

    void start();

    boolean login(String user,String password) throws IOException;

    void removeCliente(String id);

    void registaCliente(String idCliente, String nome, int nif, int telemovel, String mail, String equip);

    void registaEquip(int nif, String idEquip, String estado);

    void consultaClientes();

    void shutdown();
}
