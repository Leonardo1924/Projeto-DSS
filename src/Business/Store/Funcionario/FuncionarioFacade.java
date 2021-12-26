package Business.Store.Funcionario;

import Business.Store.IFuncionario;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FuncionarioFacade implements IFuncionario {

    public FuncionarioFacade() {
    }

    /*
     * Método que contacta um cliente via email ou via telefone.
     * Devolve a data e hora do contacto. Se for o output for null, então o cliente não foi contactado.
     *    notContacted - lista de clientes que ainda não foram contactados
     *    contacted - lista de clientes que já foram contactados
     */
    public String contactaCliente(String idCliente, List<String> contactados, List<String> naoContactados){
        String timeToString = null;
        if(naoContactados.contains(idCliente)){
            LocalDateTime time = LocalDateTime.now();
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            timeToString = time.format(timeFormat);

            naoContactados.remove(idCliente);
            contactados.add(idCliente);
        }
        return timeToString;
    }
}
