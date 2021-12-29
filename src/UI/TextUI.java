package UI;

import Business.Store.IStoreLN;
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
            System.out.println("\n\033[1;35mBem vindo ao Sistema da Loja!\033[0m\n");
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
                "Relatório de estatísticas",
                "Alterar utilizador"});

        menu.setPreCondition(5,()->this.model.getFuncionariosFacade().getTipoFuncionario(this.model.getFuncionariosFacade().getUserAtual()).equals("Gestor"));

        //Registar os handlers
        menu.setHandlers(1, this::gestaoDeClientes);
        menu.setHandlers(2, this::gestaoDeOrcamento);
        menu.setHandlers(3, this::gestaoDeEquipamentos);
        menu.setHandlers(4, this::gestaoDeServico);
        menu.setHandlers(5, this::gestaoDeEstatisticas);
        menu.setHandlers(6, () -> {
            System.out.print("Inserir username: ");
            String id = scan.nextLine();
            System.out.print("Inserir password: ");
            String pass = scan.nextLine();
            if (this.model.login(id,pass)) System.out.println("\nUtilizador alterado.\n");
            else System.out.println("\nDados fornecidos incorretos\n");
        });
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
            System.out.print("Indique o telemóvel do cliente: ");
            int lengNr = scan.nextLine().length();
            int telemovel = 0;
            if(lengNr!=9){ telemovel = verificarNrTelemovel();}
            else{ telemovel = Integer.parseInt(scan.nextLine()); }
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
                "Apagar Orçamento",
                "Consultar plano"});

        //Registar pré-condições das transições
        menu.setPreCondition(1,()->this.model.getFuncionariosFacade().getTipoFuncionario(this.model.getFuncionariosFacade().getUserAtual()).equals("Rececionista"));
        menu.setPreCondition(2,()->!this.model.getOrcamentosFacade().getOrcamentos().isEmpty());
        menu.setPreCondition(3,()->!this.model.getOrcamentosFacade().getOrcamentos().isEmpty());
        menu.setPreCondition(4,()->!this.model.getOrcamentosFacade().getOrcamentos().isEmpty());
        menu.setPreCondition(5,()->!this.model.getPlanosFacade().getPlanos().isEmpty());

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
        menu.setHandlers(5, () ->  {
            System.out.print("Indique o ID do plano a consultar: ");
            Integer id = Integer.parseInt(scan.nextLine());
            if(id <= this.model.getPlanosFacade().getPlanos().size())
                System.out.println(this.model.getPlanosFacade().getPlanos().get(id));
            else System.out.println("ID de plano inválido ");
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
                "Serviço Programado",
                "Consultar Serviço",
                "Lista de Serviços",
                "Apagar Serviço"});

        //Registar pré-condições das transições
        menu.setPreCondition(1,()->this.model.getFuncionariosFacade().getTipoFuncionario(this.model.getFuncionariosFacade().getUserAtual()).equals("Tecnico"));
        menu.setPreCondition(2,()->this.model.getFuncionariosFacade().getTipoFuncionario(this.model.getFuncionariosFacade().getUserAtual()).equals("Tecnico"));
        menu.setPreCondition(3,()->!this.model.getEquipamentosFacade().getEquipamentos().isEmpty());
        menu.setPreCondition(4,()->!this.model.getEquipamentosFacade().getEquipamentos().isEmpty());

        //Registar os handlers
        menu.setHandlers(1, this::servicoExpresso);
        menu.setHandlers(2, this::servicoProgramado);
        menu.setHandlers(3, () ->  {
            System.out.print("Indique o ID do serviço a consultar: ");
            Integer id = Integer.parseInt(scan.nextLine());
            this.model.getServicosFacade().consultaServico(id);
        });
        menu.setHandlers(4, () -> System.out.print(this.model.getServicosFacade().getServicos()));
        menu.setHandlers(5, () ->  {
            System.out.print("Indique o ID do serviço a remover: ");
            Integer id = Integer.parseInt(scan.nextLine());
            this.model.getServicosFacade().removeServico(id);
            System.out.println("O serviço foi removido com sucesso!");
        });

        menu.run();
    }

    private void servicoExpresso() throws IOException{
       Menu menu = new Menu(new String[]{
                "Arranjar ecrã",
                "Instalar Sistema Operativo",
                "Colocar película",
                "Limpar equipamento"});

       menu.setHandlers(1,()->{
           Duration tempo = Duration.parse( "PT30M");
           float custo = 60;
           this.model.getServicosFacade().adicionaServicoExpresso(custo,tempo);
       });
        menu.setHandlers(1,()->{
            Duration tempo = Duration.parse( "PT10M");
            float custo = 20;
            this.model.getServicosFacade().adicionaServicoExpresso(custo,tempo);
        });
        menu.setHandlers(3,()->{
            Duration tempo = Duration.parse( "PT5M");
            float custo = 10;
            this.model.getServicosFacade().adicionaServicoExpresso(custo,tempo);
        });
        menu.setHandlers(4,()->{
            Duration tempo = Duration.parse( "PT60M");
            float custo = 50;
            this.model.getServicosFacade().adicionaServicoExpresso(custo,tempo);
        });

       menu.run();
    }

    private void servicoProgramado() throws IOException {
        Menu menu = new Menu(new String[]{
                "Adicionar Serviço",
                "Editar Serviço",
                });

        //Registar pré-condições das transições
        menu.setPreCondition(1,()->this.model.getFuncionariosFacade().getTipoFuncionario(this.model.getFuncionariosFacade().getUserAtual()).equals("Tecnico"));
        menu.setPreCondition(2,()->this.model.getFuncionariosFacade().getTipoFuncionario(this.model.getFuncionariosFacade().getUserAtual()).equals("Tecnico"));

        //Registar os handlers
        //menu.setHandlers(1,()->);
        //menu.setHandlers(2,()->);
        //menu.setHandlers(3,()-> System.out.println(this.model.getServicosFacade().getServicos()));
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
                System.out.print("\nInserir username: ");
                user = scan.nextLine();
                System.out.print("Inserir password: ");
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

    public int verificarNrTelemovel() throws IOException {
        boolean sucesso = false;
        String telemovel = null;
            while(!sucesso){
                try{
                    System.out.print("Número de telemovel só pode ter 9 dígitos! Tente Novamente \nteste");
                    System.out.print("Indique o telemóvel do cliente: ");
                    telemovel = scan.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println(e);
                }
                if(telemovel.length() == 9){
                    sucesso = true;
                }
            }
        return Integer.parseInt(telemovel);
    }

    public void ExitScreen(boolean login){
        Menu.Logo();
        if(!login)
            System.out.println("\nNúmero máximo de tentativas excedido.");
    }
}
