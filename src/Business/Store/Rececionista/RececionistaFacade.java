package Business.Store.Rececionista;

import Business.Store.IRececionista;

public class RececionistaFacade implements IRececionista {
    Rececionista saved = new Rececionista("Rui","TalkLover");
    Rececionista userAtual;

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
