package Business.Store;

import Business.IStoreLN;

public class StoreLNFacade implements IStoreLN {
    Boolean funcional;

    public StoreLNFacade(){
        funcional = true;
    }

    public void start() {

    }

    public boolean login(String user, String password) {
        return false;
    }

    public void shutdown() {
        this.funcional = false;
    }
}
