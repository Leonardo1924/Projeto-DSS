package Business;

import java.io.IOException;

public interface IStoreLN {
    void start();

    boolean login(String user,String password) throws IOException;

    void removeCliente(int id);

    void registaCliente(String idCliente, String nome, int nif, int telemovel, String mail, String equip);

    void registaEquip(int nif, String idEquip);

    void shutdown();
}
