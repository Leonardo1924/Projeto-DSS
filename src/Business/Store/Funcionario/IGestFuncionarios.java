package Business.Store.Funcionario;

import java.io.IOException;
import java.util.List;

public interface IGestFuncionarios {

    boolean login(String username,String password) throws IOException;

    String contactaCliente(String idCliente, List<String> naoContactados, List<String> contactados);
}
