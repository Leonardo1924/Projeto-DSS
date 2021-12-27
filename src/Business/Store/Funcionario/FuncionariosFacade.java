package Business.Store.Funcionario;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FuncionariosFacade implements IGestFuncionarios {

    Map<String,Funcionario> credentials;
    private String userAtual;



    public FuncionariosFacade(Map<String,Funcionario> credentials) {
        this.credentials = credentials.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e-> e.getValue().clone()));
        this.userAtual = "";
    }

    /*
     * Método que contacta um cliente via email ou via telefone.
     * Devolve a data e hora do contacto. Se for o output for null, então o cliente não foi contactado.
     *    notContacted - lista de clientes que ainda não foram contactados
     *    contacted - lista de clientes que já foram contactados
     */
    public String getUserAtual(){
        return this.userAtual;
    }

    public boolean login(String username,String password) throws IOException {
        Boolean res = false;
        if (this.credentials.containsKey(username)) {
            this.userAtual = username;
            Funcionario func = this.credentials.get(username);
            if (password.equals(func.getPassword())) {
                res = true;
            }
        }
        return res;
    }

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

    public String getTipoFuncionario(String username){
        return this.credentials.get(username).getTipo();
    }
}
