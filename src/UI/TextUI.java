package UI;

import Business.IStoreLN;
import Business.Store.Funcionario.Funcionario;
import Business.Store.Funcionario.IGestFuncionarios;
import Business.Store.Funcionario.FuncionariosFacade;
import Business.Parser;
import Business.Store.StoreLNFacade;

import java.io.IOException;
import java.util.*;

public class TextUI {

    private IStoreLN model;
    private Scanner scan;

    public TextUI() throws IOException {
        this.model = new StoreLNFacade();
        this.scan = new Scanner(System.in);
    }

    public void run() throws IOException {
        Menu.Logo();
        boolean login = verificaLogin();
        if (login) {
            System.out.println("\033[1;35mBem vindo ao Sistema da Loja!\033[0m");
            this.menuPrincipal();
        } else {
            this.ExitScreen(login);
        }
    }

    /**
     * Estado - Menu Principal
     */
    private void menuPrincipal() throws IOException {
        Menu menu = new Menu(new String[]{
                "Cliente",
                "Orçamento",
                "Equipamento",
                "Serviço",
                "Relatório de estatísticas"});

        //Registar os handlers
        menu.setHandlers(1, this::gestaoDeClientes);
        menu.setHandlers(2, this::gestaoDeOrcamento);
        menu.setHandlers(3, this::gestaoDeEquipamentos);
        menu.setHandlers(4, this::gestaoDeServico);
        menu.setHandlers(5, this::gestaoDeEstatisticas);
        menu.run();
    }

    private void gestaoDeClientes() throws IOException {
        Menu menu = new Menu(new String[]{
                "Registar novo cliente",
                "Remover um cliente",
                "Notificar o cliente",
                "Consultar a lista de clientes"
        });

        //Registar pré-condições das transições
        menu.setPreCondition(2, () -> !this.model.getClientesFacade().getClientes().isEmpty());
        menu.setPreCondition(3, () -> !this.model.getClientesFacade().getClientes().isEmpty());
        menu.setPreCondition(4, () -> !this.model.getClientesFacade().getClientes().isEmpty());
        //Registar os handlers

        menu.setHandlers(1, () -> {
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
            this.model.getClientesFacade().registaCliente(id, nome, nif, telemovel, mail, equip);
            System.out.println("O cliente foi registado com sucesso!");
        });
        menu.setHandlers(2, () -> {
            System.out.println("Indique o ID do cliente a remover:");
            String id = scan.nextLine();
            this.model.getClientesFacade().removeCliente(id);
            System.out.println("O cliente foi removido com sucesso!");
        });
        menu.setHandlers(3, () -> {
            System.out.println("Indique o ID do cliente a contactar:");
            String id = scan.nextLine();
            List<String> contactados = this.model.getClientesFacade().getContactados();
            List<String> naoContactados = this.model.getClientesFacade().getNaoContactados();
            String time = this.model.getFuncionariosFacade().contactaCliente(id, contactados, naoContactados);
            System.out.println("O cliente foi contactado em " + time + "!");
        });
        menu.setHandlers(4, () -> {
            this.model.getClientesFacade().consultaClientes();
        });

        menu.run();
    }

    private void gestaoDeOrcamento() throws IOException {
        Menu menu = new Menu(new String[]{
                "Pedido de Orçamento",
                "Editar Orçamento",
                "Consultar Lista de Orçamentos",
                "Apagar Orçamento"});

        //Registar pré-condições das transições
        menu.setPreCondition(1,()->!this.model.getFuncionariosFacade().isRececionista(this.model.getFuncionariosFacade().getUserAtual()));
        //menu.setPreCondition(2,()->);
        //menu.setPreCondition(3,()->);
        //menu.setPreCondition(4,()->);

        //Registar os handlers
        //menu.setHandlers(1,()->);
        menu.run();
    }

    private void gestaoDeEquipamentos() throws IOException {
        Menu menu = new Menu(new String[]{
                "Registar Equipamento",
                "Consultar Estado",
                "Levantar Equipamento",
                "Apagar Equipamento"});

        //Registar pré-condições das transições
        menu.setPreCondition(2,()->!this.model.getEquipamentosFacade().getEquipamentos().isEmpty());
        menu.setPreCondition(3,()->!this.model.getEquipamentosFacade().getEquipamentos().isEmpty());
        menu.setPreCondition(4,()->!this.model.getEquipamentosFacade().getEquipamentos().isEmpty());
        //Registar os handlers

        menu.setHandlers(1, () -> {
            System.out.println("Indique o NIF do cliente:");
            int nif = Integer.parseInt(scan.nextLine());
            System.out.println("Indique o ID do equipamento:");
            String equip = scan.nextLine();
            this.model.getEquipamentosFacade().registaEquip(nif, equip, "em processo");
            System.out.println("O equipamento foi registado com sucesso!");
        });
        menu.setHandlers(2, () -> {
            System.out.println("Indique o NIF do cliente:");
            int nif = Integer.parseInt(scan.nextLine());
            String estado = this.model.getEquipamentosFacade().consultaEstado(nif);
            System.out.println("O equipamento está " + estado + "!");
        });
        menu.setHandlers(3, () -> {
            System.out.println("Indique o NIF do cliente:");
            int nif = Integer.parseInt(scan.nextLine());
            this.model.getEquipamentosFacade().levantaEquipamento(nif);
            System.out.println("O equipamento foi levantado com sucesso!");
        });
        menu.setHandlers(4, () -> {
            System.out.println("Indique o NIF do cliente:");
            int nif = Integer.parseInt(scan.nextLine());
            this.model.getEquipamentosFacade().apagaEquipamento(nif);
            System.out.println("O equipamento foi apagado com sucesso!");
        });

        menu.run();
    }

    private void gestaoDeServico() throws IOException {
        Menu menu = new Menu(new String[]{
                "Serviço Expresso",
                "Serviço Programado"});

        menu.setHandlers(1,this::servicoExpresso);
        menu.setHandlers(2, this::gestaoDoPlano);
        menu.run();
    }

    private void servicoExpresso() throws IOException{
        System.out.println("Somos tão rapidos que ja esta arranjado");
    }

    private void gestaoDoPlano() throws IOException {
        Menu menu = new Menu(new String[]{
                "Elaborar Plano de Trabalhos",
                "Editar Plano de Trabalhos",
                "Consultar Plano",
                "Apagar Plano"});

        //Registar pré-condições das transições
        //menu.setPreCondition(2,()->têm que existir um plano para editar);
        //menu.setPreCondition(3,()->têm que existir um plano para consultar);
        //menu.setPreCondition(4,()->têm que existir um plano para apagar);
        //Registar os handlers

        menu.run();
    }

    private void gestaoDeEstatisticas() throws IOException {
        Menu menu = new Menu(new String[]{
                "Novo Relatório",
                "Ler Relatório",
                "Editar Relatório",
                "Apagar Relatório"});

        //Registar pré-condições das transições

        //Registar os handlers
        menu.run();
    }

    public boolean verificaLogin() throws IOException {
        String user = null;
        String password = null;
        boolean sucesso = false;
        int tentativas = 0;

        if (tentativas == 3)
            System.out.println("\n\n O Sistema será encerrado agora");

        while (!sucesso && tentativas < 3) {
            try {
                System.out.println("\nInsire o seu nome:");
                user = scan.nextLine();
                System.out.println("Insira a sua password:");
                password = scan.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(e.toString());
            }
            sucesso = this.model.login(user, password);

            if (!sucesso && ++tentativas < 3) {
                System.out.println("Dados Inválidos,tente novamente.\n" + "Tentativas restantes: " + (3 - tentativas));
            }
        }
        return sucesso;
    }

    public void ExitScreen(boolean login){
        Menu.Logo();
        if(!login)
            System.out.println("\nNúmero de tentativas excedido");
            System.out.println("\nO sistema será encerrado agora");
            System.out.println("\033[1;36m" + "Sessão Terminada!" + "\033[0m");
    }
}
