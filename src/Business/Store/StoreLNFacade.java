package Business.Store;

import Business.*;
import Business.Store.Funcionario.*;
import Business.Parser;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class StoreLNFacade implements IStoreLN {
    private boolean funcional;
    private Map<String,Funcionario> credentials;
    // private Map<String,Equipamento> equipments;

    public StoreLNFacade(boolean funcional, Map<String,Funcionario> credentials/*,Map<String,Equipamento> equipments*/){
        this.funcional = funcional;
        this.credentials = credentials.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e-> e.getValue()));
        // this.equipments = equipments.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e-> e.getValue()));
    }

    public void start() {

    }

    public boolean login(String username,String password) throws IOException {
        Boolean res = false;
        if (this.credentials.containsKey(username)) {
            Funcionario func = this.credentials.get(username);
            if (password.equals(func.getPassword())) {
                //this.userAtual = func;
                res = true;
            }
        }
        return res;
    }

    public void shutdown() {
        this.funcional = false;
    }
}
