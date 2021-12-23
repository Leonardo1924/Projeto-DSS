package Business.Store;

import Business.IStoreLN;

public class StoreLNFacade implements IStoreLN {
    Boolean funcional;

    public StoreLNFacade(){
        funcional = true;
    }

    public void start() {

    }

    public boolean login(String username,String password) {
        if (credentials.containsKey(username)) {
            Funcionario func = credentials.get(username);
            if (password == func.getPassword()) {
                this.userAtual = func;
                return true;
            }
        }
        else return false;
    }

    public void shutdown() {
        this.funcional = false;
    }
}
