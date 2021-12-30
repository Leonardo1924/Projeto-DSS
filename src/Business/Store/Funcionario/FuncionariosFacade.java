package Business.Store.Funcionario;

import Business.Store.Equipamento.Equipamento;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FuncionariosFacade implements IGestFuncionarios {

    Map<String,Funcionario> credentials;
    private String userAtual;
    private Map<String, List<String>> rececao; // username do rececionista, id do equipamento
    private Map<String, List<String>>  entrega;

    public FuncionariosFacade(Map<String,Funcionario> credentials) {
        this.credentials = credentials.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e-> e.getValue().clone()));
        this.userAtual = "";
    }

    public Map<String,Funcionario> getFuncionarios(){
        return this.credentials.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e-> e.getValue().clone()));
    }

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

    public boolean validateFuncionario(String idTecnico){
        return this.credentials.containsKey(idTecnico);
    }

    public void equipRecebidos(Equipamento equip){
        List<String> list = new ArrayList<String>();
        if(this.rececao.containsKey(this.userAtual)){
            list = this.rececao.get(this.userAtual);
            list.add(equip.getId());
            this.rececao.put(this.userAtual,list);
        }else {
            list.add(equip.getId());
            this.rececao.put(this.userAtual,list);
        }
    }

    public void equipDevolvidos(Equipamento equip){
        List<String> list = new ArrayList<String>();
        if(this.entrega.containsKey(this.userAtual)){
            list = this.entrega.get(this.userAtual);
            list.add(equip.getId());
            this.entrega.put(this.userAtual,list);
        }else {
            list.add(equip.getId());
            this.entrega.put(this.userAtual,list);
        }
    }
}
