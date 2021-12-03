package UI;

import java.util.*;

public class TextUI {

    public void run(){
        System.out.println("\033[1;35mBem vindo ao Sistema da Loja!\033[0m");
        this.menuPrincipal();
        System.out.println("\033[1;36m"+"Sessão Terminada!"+"\033[0m");
    }

    /**
     * Estado - Menu Principal
     */
    private void menuPrincipal() {
        Menu menu = new Menu(new String[]{
                "Criar ficha Cliente",
                "Realizar Pedido de Orçamento",
                "Consultar estado do Equipamento",
                "Registar plano de trabalhos",
                "Notificar o cliente",
                "Consultar relatório de estatísticas"});


        //Registar pré-condições das transições


        //Registar os handlers
        menu.setHandlers(1,()->gestaoDeClientes());

        menu.run();
    }

    private void gestaoDeClientes(){
        Menu menu = new Menu(new String[]{
                "Registrar novo cliente",
                "Remover um cliente",
                "Verificar a lista de cliente"});

        //Registar pré-condições das transições

        //Registar os handlers


        menu.run();

    }
}
