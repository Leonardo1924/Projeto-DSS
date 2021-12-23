package Business.Store;

import Business.*;
import Business.Store.Funcionario.*;
import Business.Parser;
import Data.SGRE;

import java.io.IOException;
import java.util.Map;

public class StoreLNFacade implements IStoreLN {
    private Boolean funcional;

    public StoreLNFacade(){
        funcional = true;
    }

    public void start() {

    }

    public boolean login(String username,String password) throws IOException {
        SGRE data = Parser.parse();
        Map<String,Funcionario> credentials = data.getLoginData();
        Boolean res = false;
        if (credentials.containsKey(username)) {
            Funcionario func = credentials.get(username);
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
