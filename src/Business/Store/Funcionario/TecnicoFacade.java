package Business.Store.Funcionario;

import Business.Store.ITécnico;

public class TecnicoFacade extends Funcionario implements ITécnico {
    Tecnico saved = new Tecnico("Tiago", "RepairLover");
    Tecnico userAtual;

    public TecnicoFacade(String username, String password) {
        super(username, password);
    }

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
}

