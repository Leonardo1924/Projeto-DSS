package Business.Cliente;

import java.util.List;
import java.util.Map;

public interface IGestClientes {

    List<String> getContactados();

    List<String> getNaoContactados();

    Map<String,Cliente> getClientes();

    void removeCliente(String id);

    void registaCliente(String idCliente, String nome, int nif, int telemovel, String mail, String idEquip);

    void consultaClientes();
}
