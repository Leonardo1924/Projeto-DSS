package UI;

import Business.IStoreLN;
import Business.Parser;

import java.io.IOException;
import java.util.*;

public class TextUI {

    private IStoreLN model;
    private Scanner scan;

    public TextUI() throws IOException {
        this.model = Parser.parse();
        this.scan = new Scanner(System.in);
    }

    public void run() throws IOException {
        System.out.println("\033[1;35mBem vindo ao Sistema da Loja!\033[0m");
        this.menuPrincipal();
        System.out.println("\n\n O sistema será encerrado agora");
        System.out.println("\033[1;36m"+"Sessão Terminada!"+"\033[0m");
    }

    /**
     * Estado - Menu Principal
     */
    private void menuPrincipal() throws IOException {
        Menu menu = new Menu(new String[]{
                "Cliente",
                "Orçamento",
                "Equipamento",
                "Plano de trabalhos",
                "Relatório de estatísticas"});

        //Registar pré-condições das transições
        //Registar os handlers

        menu.setHandlers(1,()-> {
            try {
                gestaoDeClientes();
            } catch (IOException e) {
                e.printStackTrace();
            }});
        menu.setHandlers(2,()-> {
            try {
                gestaoDeOrçamento();
            } catch (IOException e) {
                e.printStackTrace();
            }});
        menu.setHandlers(3,()-> {
            try {
                gestaoDeEquipamentos();
            } catch (IOException e) {
                e.printStackTrace();
            }});
        menu.setHandlers(4,()-> {
            try {
                gestaoDoPlano();
            } catch (IOException e) {
                e.printStackTrace();
            }});
        menu.setHandlers(5,()-> {
            try {
                gestaoDeEstatisticas();
            } catch (IOException e) {
                e.printStackTrace();
            }});

        menu.run();
    }

    private void gestaoDeClientes() throws IOException {
        Menu menu = new Menu(new String[]{
                "Registar novo cliente",
                "Remover um cliente",
                "Notificar o cliente",
                "Verificar a lista de cliente"
        });

        //Registar pré-condições das transições
        //Registar os handlers

        menu.setHandlers(1,()-> {
            System.out.println("Indique o ID do cliente:");
            String id = scan.nextLine();
            System.out.println("Indique o nome do cliente:");
            String nome = scan.nextLine();
            System.out.println("Indique o nif do cliente:");
            int nif = Integer.parseInt(scan.nextLine());
            System.out.println("Indique o telemovel do cliente:");
            int telemovel = Integer.parseInt(scan.nextLine());
            System.out.println("Indique o email do cliente:");
            String mail = scan.nextLine();
            System.out.println("Indique o ID do equipamento:");
            String equip = scan.nextLine();
            this.model.registaCliente(id, nome, nif, telemovel, mail, equip);
            System.out.println("O cliente foi registado com sucesso!");
        });
        menu.setHandlers(2,()-> {
            System.out.println("Indique o ID do cliente a remover:");
            String id = scan.nextLine();
            this.model.removeCliente(id);
            System.out.println("O cliente foi removido com sucesso!");
        });
        menu.setHandlers(3,()-> {
            try {
                gestaoDeEquipamentos();
            } catch (IOException e) {
                e.printStackTrace();
            }});
        menu.setHandlers(4,()-> {
            try {
                gestaoDoPlano();
            } catch (IOException e) {
                e.printStackTrace();
            }});

        menu.run();
    }

    private void gestaoDeOrçamento() throws IOException {
        Menu menu = new Menu(new String[]{
                "Pedido de Orçamento",
                "Editar Orçamento",
                "Consultar Lista de Orçamentos",
                "Apagar Orçamento"});

        //Registar pré-condições das transições

        //Registar os handlers

        menu.run();
    }

    private void gestaoDeEquipamentos() throws IOException {
        Menu menu = new Menu(new String[]{
                "Registar Equipamento",
                "Consultar Estado",
                "Levantamento do Equipamento",
                "Apagar Equipamento"});

        //Registar pré-condições das transições

        //Registar os handlers

        menu.setHandlers(1,()-> {
            System.out.println("Indique o NIF do cliente:");
            int nif = Integer.parseInt(scan.nextLine());
            System.out.println("Indique o ID do equipamento:");
            String equip = scan.nextLine();
            this.model.registaEquip(nif,equip);
            System.out.println("O equipamento foi registado com sucesso!");
        });
        menu.setHandlers(2,()-> {
            try {
                gestaoDeEquipamentos();
            } catch (IOException e) {
                e.printStackTrace();
            }});
        menu.setHandlers(3,()-> {
            try {
                gestaoDeEquipamentos();
            } catch (IOException e) {
                e.printStackTrace();
            }});
        menu.setHandlers(4,()-> {
            try {
                gestaoDoPlano();
            } catch (IOException e) {
                e.printStackTrace();
            }});
        menu.setHandlers(5,()-> {
            try {
                gestaoDeEstatisticas();
            } catch (IOException e) {
                e.printStackTrace();
            }});

        menu.run();
    }

    private void gestaoDoPlano() throws IOException {
        Menu menu = new Menu(new String[]{
                "Elaborar Plano de Trabalhos",
                "Editar Plano de Trabalhos",
                "Consultar Plano",
                "Apagar Plano"});

        //Registar pré-condições das transições

        //Registar os handlers

        menu.run();
    }

    private void gestaoDeEstatisticas() throws IOException {
        Menu menu = new Menu(new String[]{
                "Ler Relatório",
                "Editar Relatório",
                "Apagar Relatório"});

        //Registar pré-condições das transições

        //Registar os handlers

        menu.run();
    }
}
