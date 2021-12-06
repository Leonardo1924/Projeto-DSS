package Business.Store.Funcionario;

import Business.Store.ITécnico;

public class TécnicoFacade extends Funcionario implements ITécnico {
    Técnico saved = new Técnico("Tiago", "RepairLover");
    Técnico userAtual;

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

