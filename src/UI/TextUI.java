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
                "1.Criar ficha Cliente",
                "2.Realizar Pedido de Orçamento",
                "3.Consultar estado do Equipamento",
                "4.Registar plano de trabalhos",
                "5.Notificar o cliente",
                "6.Consultar relatório de estatísticas"});


        //Registar pré-condições das transições


        //Registar os handlers


        menu.run();
    }

    private void gestaoDeClientes(){
        Menu menu = new Menu(new String[]{
                "1.Registrar novo cliente",
                "2.Remover um cliente",
                "3.Verificar a lista de cliente"});

        //Registar pré-condições das transições

        //Registar os handlers


        menu.run();

    }
}
