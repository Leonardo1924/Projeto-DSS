package Business.Store.Gestor;

import Business.Store.IGestor;

public class GestorFacade implements IGestor {
    Gestor saved = new Gestor("João", "MathLover");
    Gestor userAtual;


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