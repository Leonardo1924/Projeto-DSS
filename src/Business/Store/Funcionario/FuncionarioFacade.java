package Business.Store.Funcionario;

import Business.Cliente.Cliente;
import Business.Store.IFuncionario;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FuncionarioFacade implements IFuncionario {
    Funcionario saved = new Funcionario("Rui","TalkLover");
    Funcionario userAtual;

    /*
    public FuncionarioFacade() {
    }
    */

    public boolean login(String user, String password) {
        boolean res = false;
    /*
        if (saved.userExiste(user)) {
            Gestor g = saved.get(user);
            if ((res = g.rightpassWord(password)))
                this.userAtual = g;
        }

     */
        return res;
    }

    /*
    Método que contacta um cliente via email ou via telefone.
    Devolve a data e hora do contacto. Se for o output for null, então o cliente não foi contactado.
        notContacted - lista de clientes que ainda não foram contactados
        contacted - lista de clientes que já foram contactados
    */
    public String contactClient(Cliente client, List<Cliente> notContacted, List<Cliente> contacted){
        String timeToString = null;
        if(notContacted.contains(client)){
            LocalDateTime time = LocalDateTime.now();
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            timeToString = time.format(timeFormat);

            notContacted.remove(client);
            contacted.add(client);
        }
        return timeToString;
    }
}
