package Business.Store.Funcionario;

import Business.Cliente.Cliente;
import Business.Store.IFuncionario;
import Business.Parser;
import Data.SGRE;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FuncionarioFacade implements IFuncionario {
    SGRE data = Parser.parse();
    Map<String,Funcionario> credentials = data.getLoginData();
    Funcionario userAtual;

    public FuncionarioFacade() throws IOException {
    }
    /*
    public FuncionarioFacade() {
    }
    */

    public boolean login(String username,String password) {
        System.out.println("Fuck");
        boolean res = false;
        boolean credential = false;
        credential = credentials.containsKey(username);
        System.out.println("credential: " + credential);
        if (credentials.containsKey(username)) {
            Funcionario func = credentials.get(username);
            System.out.println("password: " + func.getPassword());
            if (password == func.getPassword()) {
                this.userAtual = func;
                res = true;
            }
        }
        System.out.println("res: " + res);
        return res;
    }

    /*
     * Método que contacta um cliente via email ou via telefone.
     * Devolve a data e hora do contacto. Se for o output for null, então o cliente não foi contactado.
     *    notContacted - lista de clientes que ainda não foram contactados
     *    contacted - lista de clientes que já foram contactados
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
