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
                "Cliente",
                "Orçamento",
                "Equipamento",
                "Plano de trabalhos",
                "Relatório de estatísticas"});

        //Registar pré-condições das transições


        //Registar os handlers
        menu.setHandlers(1,()->gestaoDeClientes());
        menu.setHandlers(2,()->gestaoDeOrçamento());
        menu.setHandlers(3,()->gestaoDeEquipamentos());
        menu.setHandlers(4,()->gestaoDoPlano());
        menu.setHandlers(5,()->gestaoDeEstatisticas());

        menu.run();
    }

    private void gestaoDeClientes(){
        Menu menu = new Menu(new String[]{
                "Registrar novo cliente",
                "Remover um cliente",
                "Notificar o cliente",
                "Verificar a lista de cliente"});

        //Registar pré-condições das transições

        //Registar os handlers


        menu.run();

    }

    private void gestaoDeOrçamento(){
        Menu menu = new Menu(new String[]{
                "Pedido de Orçamento",
                "Editar Orçamento",
                "Consultar Lista de Orçamentos",
                "Apagar Orçamento"});

        //Registar pré-condições das transições

        //Registar os handlers

        menu.run();
    }

    private void gestaoDeEquipamentos(){
        Menu menu = new Menu(new String[]{
                "Registar Equipamento",
                "Consultar Estado",
                "Levantamento do Equipamento",
                "Apagar Equipamento"});

        //Registar pré-condições das transições

        //Registar os handlers

        menu.run();
    }

    private void gestaoDoPlano(){
        Menu menu = new Menu(new String[]{
                "Elaborar Plano de Trabalhos",
                "Editar Plano de Trabalhos",
                "Consultar Plano",
                "Apagar Plano"});

        //Registar pré-condições das transições

        //Registar os handlers

        menu.run();
    }

    private void gestaoDeEstatisticas(){
        Menu menu = new Menu(new String[]{
                "Ler Relatório",
                "Editar Relatório",
                "Apagar Relatório"});

        //Registar pré-condições das transições

        //Registar os handlers

        menu.run();
    }
}
