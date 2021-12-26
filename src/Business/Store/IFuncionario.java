package Business.Store;

import java.util.List;

public interface IFuncionario {

    String contactaCliente(String idCliente, List<String> naoContactados, List<String> contactados);
}
