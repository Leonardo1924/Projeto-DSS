package UI;

import Business.IStoreLN;
import Business.Saver;
import Business.Store.StoreLNFacade;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
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
        // Saver.gravar(this.model);
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
            System.out.print("Indique o ID do cliente: ");
            String id = scan.nextLine();
            System.out.print("Indique o nome do cliente: ");
            String nome = scan.nextLine();
            System.out.print("Indique o nif do cliente: ");
            int nif = Integer.parseInt(scan.nextLine());
            System.out.print("Indique o telemovel do cliente: ");
            int telemovel = Integer.parseInt(scan.nextLine());
            System.out.print("Indique o email do cliente: ");
            String mail = scan.nextLine();
            System.out.print("Indique o ID do equipamento: ");
            String equip = scan.nextLine();
            if(this.model.getClientesFacade().registaCliente(id, nome, nif, telemovel, mail, equip))
                System.out.println("O cliente foi registado com sucesso!");
            else System.out.println("Não foi possível registar o cliente introduzido!");
        });
        menu.setHandlers(2, () -> {
            System.out.print("Indique o ID do cliente a remover: ");
            String id = scan.nextLine();
            this.model.getClientesFacade().removeCliente(id);
            System.out.println("O cliente foi removido com sucesso!");
        });
        menu.setHandlers(3, () -> {
            System.out.print("Indique o ID do cliente a contactar: ");
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
        menu.setPreCondition(1,()->this.model.getFuncionariosFacade().getTipoFuncionario(this.model.getFuncionariosFacade().getUserAtual()).equals("Rececionista"));
        menu.setPreCondition(2,()->!this.model.getOrcamentosFacade().getOrcamentos().isEmpty());
        menu.setPreCondition(3,()->!this.model.getOrcamentosFacade().getOrcamentos().isEmpty());
        menu.setPreCondition(4,()->!this.model.getOrcamentosFacade().getOrcamentos().isEmpty());

        //Registar os handlers
        menu.setHandlers(1, () -> {
            System.out.print("Indique o ID do equipamento: ");
            String idEq = scan.nextLine();
            System.out.print("Descrição do problema: ");
            String notas = scan.nextLine();
            int idOrc = this.model.getOrcamentosFacade().getOrcamentos().get(this.model.getOrcamentosFacade().getOrcamentos().size()).getIdOrcamento()+1;
            this.model.getOrcamentosFacade().registaOrcamento(idOrc,idEq,LocalDateTime.now(),notas);
        });

        menu.setHandlers(2, () -> {
            System.out.print("Indique o ID do orçamento a alterar: ");
            int idOrc = Integer.parseInt(scan.nextLine());
            System.out.print("Indique o ID do técnico responsável: ");
            String idTecnico = scan.nextLine();
            if(this.model.getFuncionariosFacade().validateFuncionario(idTecnico)) {
                if (this.model.getFuncionariosFacade().getTipoFuncionario(idTecnico).equals("Tecnico")) {
                    boolean regista = true;
                    int idPlano = this.model.getPlanosFacade().getPlanos().size()+1;
                    this.model.getPlanosFacade().adicionaPlano(idPlano,idOrc,idTecnico);
                    while(regista) {
                        System.out.print("Passo a executar: ");
                        String desc = scan.nextLine();
                        System.out.print("Custo: ");
                        float custo = scan.nextFloat();
                        System.out.print("Duração: ");
                        int val = scan.nextInt();
                        Duration prazo = Duration.parse("PT" + val + "M");
                        this.model.getPlanosFacade().atualizaPlano(idPlano,desc,custo,prazo);
                        System.out.print("Continuar? sim / não ");
                        scan.nextLine();
                        String opt = scan.nextLine();
                        regista = opt.equals("sim");
                    }
                    float custoTotal = this.model.getPlanosFacade().getPlanos().get(idPlano).getCusto();
                    Duration prazoTotal = this.model.getPlanosFacade().getPlanos().get(idPlano).getPrazo();
                    this.model.getOrcamentosFacade().atualizaOrcamento(idOrc,idTecnico,idPlano,custoTotal,prazoTotal);
                }
            }
            else System.out.println("Funcionário inválido.");
        });
        menu.setHandlers(3, () ->  this.model.getOrcamentosFacade().consultaOrcamentos());
        menu.setHandlers(4, () ->  {
            System.out.print("Indique o ID do orçamento a remover: ");
            Integer id = Integer.parseInt(scan.nextLine());
            this.model.getOrcamentosFacade().removeOrcamento(id);
            System.out.println("O orçamento foi removido com sucesso!");
        });

        menu.run();
    }

    private void gestaoDeEquipamentos() throws IOException {
        Menu menu = new Menu(new String[]{
                "Registar Equipamento",
                "Consultar Estado",
                "Levantar Equipamento",
                "Apagar Equipamento"});

        //Registar pré-condições das transições
        menu.setPreCondition(1,()->this.model.getFuncionariosFacade().getTipoFuncionario(this.model.getFuncionariosFacade().getUserAtual()).equals("Rececionista"));
        menu.setPreCondition(2,()->!this.model.getEquipamentosFacade().getEquipamentos().isEmpty());
        menu.setPreCondition(3,()->!this.model.getEquipamentosFacade().getEquipamentos().isEmpty());
        menu.setPreCondition(4,()->!this.model.getEquipamentosFacade().getEquipamentos().isEmpty());
        //Registar os handlers

        menu.setHandlers(1, () -> {
            System.out.print("Indique o NIF do cliente: ");
            int nif = Integer.parseInt(scan.nextLine());
            System.out.print("Indique o ID do equipamento: ");
            String equip = scan.nextLine();
            if(this.model.getEquipamentosFacade().registaEquip(nif, equip, "em processo"))
                System.out.println("O equipamento foi registado com sucesso!");
            else System.out.println("Não foi possível registar o equipamento!");
        });
        menu.setHandlers(2, () -> {
            System.out.print("Indique o NIF do cliente: ");
            int nif = Integer.parseInt(scan.nextLine());
            String estado = this.model.getEquipamentosFacade().consultaEstado(nif);
            System.out.println("O equipamento está " + estado + "!");
        });
        menu.setHandlers(3, () -> {
            System.out.print("Indique o NIF do cliente: ");
            int nif = Integer.parseInt(scan.nextLine());
            this.model.getEquipamentosFacade().levantaEquipamento(nif);
            System.out.println("O equipamento foi levantado com sucesso!");
        });
        menu.setHandlers(4, () -> {
            System.out.print("Indique o NIF do cliente: ");
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

        menu.setHandlers(1, this::servicoExpresso);
        menu.setHandlers(2, this::gestaoDoPlano);
        menu.run();
    }

    private void servicoExpresso() throws IOException{
       Menu menu = new Menu(new String[]{
                "Arranjar Ecrã",
                "Instalar OS",
                "Colocar Película",
                "Comprar Equipamento Novo"});

       //menu.setHandlers(1,()->);
       //menu.setHandlers(2,()->);
       //menu.setHandlers(3,()->);
       //menu.setHandlers(4,()->);
       menu.run();
    }

    private void gestaoDoPlano() throws IOException {
        Menu menu = new Menu(new String[]{
                "Elaborar Plano de Trabalhos",
                "Editar Plano de Trabalhos",
                "Consultar Plano",
                "Apagar Plano"});

        //Registar pré-condições das transições
        menu.setPreCondition(1,()->this.model.getFuncionariosFacade().getTipoFuncionario(this.model.getFuncionariosFacade().getUserAtual()).equals("Tecnico"));
        //menu.setPreCondition(2,()->têm que existir um plano para editar);
        //menu.setPreCondition(3,()->têm que existir um plano para consultar);
        //menu.setPreCondition(4,()->têm que existir um plano para apagar);

        //Registar os handlers

        //menu.setHandlers(1,()->);
        //menu.setHandlers(2,()->);
        //menu.setHandlers(3,()->);
        //menu.setHandlers(4,()->);
        menu.run();
    }

    private void gestaoDeEstatisticas() throws IOException {
        Menu menu = new Menu(new String[]{
                "Novo Relatório",
                "Ler Relatório",
                "Editar Relatório",
                "Apagar Relatório"});

        //Registar pré-condições das transições
        menu.setPreCondition(1,()->this.model.getFuncionariosFacade().getTipoFuncionario(this.model.getFuncionariosFacade().getUserAtual()).equals("Gestor"));
        //menu.setPreCondition(2,()->têm que existir um Relatorio para editar);
        //menu.setPreCondition(3,()->têm que existir um Relatorio para consultar);
        //menu.setPreCondition(4,()->têm que existir um Relatorio para apagar);

        //Registar os handlers
        //menu.setHandlers(1,()->);
        //menu.setHandlers(2,()->);
        //menu.setHandlers(3,()->);
        //menu.setHandlers(4,()->);
        menu.run();
    }

    public boolean verificaLogin() throws IOException {
        String user = null;
        String password = null;
        boolean sucesso = false;
        int tentativas = 0;

        if (tentativas == 3)
            System.out.println("\n\n O Sistema será encerrado agora.");

        while (!sucesso && tentativas < 3) {
            try {
                System.out.print("\nInsire o seu nome: ");
                user = scan.nextLine();
                System.out.print("Insira a sua password: ");
                password = scan.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(e);
            }
            sucesso = this.model.login(user, password);

            if (!sucesso && ++tentativas < 3) {
                System.out.println("Dados Inválidos, tente novamente.\n" + "Tentativas restantes: " + (3 - tentativas));
            }
        }
        return sucesso;
    }

    public void ExitScreen(boolean login){
        Menu.Logo();
        if(!login)
            System.out.println("\nNúmero máximo de tentativas excedido.");
    }
}
