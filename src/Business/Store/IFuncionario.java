package Business.Store;

import Business.Cliente.Cliente;

import java.util.List;

public interface IFuncionario {

    // boolean login(String user,String password);

    String contactClient(Cliente client, List<Cliente> notContacted, List<Cliente> contacted);
}
