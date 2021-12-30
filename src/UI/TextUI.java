package UI;

import Business.InPutOutPut.Saver;
import Business.Store.Equipamento.Equipamento;
import Business.Store.Estatisticas;
import Business.Store.IStoreLN;
import Business.Store.Servico.Servico;
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
        //Saver.gravar(this.model);
    }

    /**
     * Estado - Menu Principal
     */
    private void menuPrincipal() throws NumberFormatException, IOException {
        Menu menu = new Menu(new String[]{
                "Cliente",
                "Orçamento",
                "Equipamento",
                "Serviço",
                "Relatório de estatísticas",
                "Consultar plano de trabalho",
                "Alterar utilizador"});

        menu.setPreCondition(5,()->this.model.getFuncionariosFacade().getTipoFuncionario(this.model.getFuncionariosFacade().getUserAtual()).equals("Gestor"));
        menu.setPreCondition(6,()->!this.model.getPlanosFacade().getPlanos().isEmpty());
        //Registar os handlers
        menu.setHandlers(1, this::gestaoDeClientes);
        menu.setHandlers(2, this::gestaoDeOrcamento);
        menu.setHandlers(3, this::gestaoDeEquipamentos);
        menu.setHandlers(4, this::gestaoDeServico);
        menu.setHandlers(5, this::gestaoDeEstatisticas);
        menu.setHandlers(6, () ->  {
            System.out.print("Indique o ID do plano a consultar: ");
            try {
            int id = Integer.parseInt(scan.nextLine());
                if (id <= this.model.getPlanosFacade().getPlanos().size() && this.model.getPlanosFacade().existePlano(id))
                    System.out.print(this.model.getPlanosFacade().getPlanos().get(id));
                else System.out.print("\u001B[31mID de plano inválido!\n\n\u001b[0m");
            }
            catch (NumberFormatException e){ System.out.println("\u001B[31mERROR: ID tem de ser um inteiro!\n\n\u001b[0m"); }
        });
        menu.setHandlers(7, () -> {
            System.out.print("Inserir username: ");
            String id = scan.nextLine();
            System.out.print("Inserir password: ");
            String pass = scan.nextLine();
            if (this.model.login(id,pass)) {
                System.out.print("\nUtilizador alterado.\n\n");
            }
            else System.out.print("\u001B[31m\nDados fornecidos incorretos\n\u001b[0m");
        });
        menu.run();
    }

    private void gestaoDeClientes() throws IOException {
        Menu menu = new Menu(new String[]{
                "Registar novo cliente",
                "Remover um cliente",
                "Notificar o cliente",
                "Consultar a lista de clientes",
        });

        //Registar pré-condições das transições
        menu.setPreCondition(2, () -> !this.model.getClientesFacade().getClientes().isEmpty());
        menu.setPreCondition(3, () -> !this.model.getClientesFacade().getClientes().isEmpty());
        menu.setPreCondition(4, () -> !this.model.getClientesFacade().getClientes().isEmpty());


        //Registar os handlers
        menu.setHandlers(1, () -> {
            System.out.print("Indique o ID do cliente: ");
            String id = scan.nextLine();
            if(!this.model.getClientesFacade().existeCliente(id)){
                System.out.print("Indique o nome do cliente: ");
                String nome = scan.nextLine();
                System.out.print("Indique o nif do cliente: ");
                String lengNif = scan.nextLine();
                int nif = 0;
                if(lengNif.length() == 9){ nif = Integer.parseInt(lengNif);}
                else{ nif = verificarNif(); }
                System.out.print("Indique o telemóvel do cliente: ");
                String lengNr = scan.nextLine();
                int telemovel = 0;
                if(lengNr.length() != 9){ telemovel = verificarNrTelemovel();}
                else{ telemovel = Integer.parseInt(lengNr); }
                System.out.print("Indique o email do cliente: ");
                String mail = scan.nextLine();
                System.out.print("Indique o ID do equipamento: ");
                String equip = scan.nextLine();
                this.model.getClientesFacade().registaCliente(id, nome, nif, telemovel, mail, equip);
                System.out.print("O cliente foi registado com sucesso!\n\n");
            }
            else System.out.print("\u001B[31mEste ID de cliente não se encontra disponivel!\n\n\u001b[0m");
        });
        menu.setHandlers(2, () -> {
            System.out.print("Indique o ID do cliente a remover: ");
            String id = scan.nextLine();
            if(this.model.getClientesFacade().removeCliente(id))
                System.out.print("O cliente foi removido com sucesso!\n\n");
            else  System.out.print("\u001B[31mEste cliente não existe no sistema!\n\n\u001b[0m");
        });
        menu.setHandlers(3, () -> {
            System.out.print("Indique o ID do cliente a contactar: ");
            String id = scan.nextLine();
            if(this.model.getClientesFacade().existeCliente(id)) {
                List<String> contactados = this.model.getClientesFacade().getContactados();
                List<String> naoContactados = this.model.getClientesFacade().getNaoContactados();
                String time = this.model.getFuncionariosFacade().contactaCliente(id, contactados, naoContactados);
                System.out.print("O cliente foi contactado em " + time + "!\n\n");
            }
            else System.out.print("\u001B[31mEste cliente não existe no sistema!\n\n\u001b[0m");
        });
        menu.setHandlers(4, () -> {
            this.model.getClientesFacade().consultaClientes();
            System.out.println("\n\n");
        });

        menu.run();
    }

    private void gestaoDeOrcamento() throws NumberFormatException, IOException {
        Menu menu = new Menu(new String[]{
                "Pedido de Orçamento",
                "Editar Orçamento",
                "Consultar Lista de Orçamentos",
                "Apagar Orçamento"
        });

        //Registar pré-condições das transições
        menu.setPreCondition(1,()->this.model.getFuncionariosFacade().getTipoFuncionario(this.model.getFuncionariosFacade().getUserAtual()).equals("Rececionista"));
        menu.setPreCondition(2,()->!this.model.getOrcamentosFacade().getOrcamentos().isEmpty() && this.model.getFuncionariosFacade().getTipoFuncionario(this.model.getFuncionariosFacade().getUserAtual()).equals("Tecnico"));
        menu.setPreCondition(3,()->!this.model.getOrcamentosFacade().getOrcamentos().isEmpty());
        menu.setPreCondition(4,()->!this.model.getOrcamentosFacade().getOrcamentos().isEmpty() && this.model.getFuncionariosFacade().getTipoFuncionario(this.model.getFuncionariosFacade().getUserAtual()).equals("Tecnico"));

        //Registar os handlers
        menu.setHandlers(1, () -> {
            System.out.print("Indique o ID do equipamento: ");
            String idEq = scan.nextLine();
            if(this.model.getEquipamentosFacade().existeEquipamentoID(idEq)){
                System.out.print("Descrição do problema: ");
                String notas = scan.nextLine();
                int idOrc = this.model.getOrcamentosFacade().getOrcamentos().get(this.model.getOrcamentosFacade().getOrcamentos().size()).getIdOrcamento() + 1;
                this.model.getOrcamentosFacade().registaOrcamento(idOrc,idEq,LocalDateTime.now(),notas);
                this.model.getEquipamentosFacade().equipEstado(idEq, "no armazem");
                System.out.println("\n\n");
            }
            else System.out.print("\u001B[31mEste equipamento não existe no sistema!\n\n\u001b[0m");
        });
        menu.setHandlers(2, () -> {
            System.out.print("Indique o ID do orçamento a alterar: ");
            try {
                int idOrc = Integer.parseInt(scan.nextLine());
                if (this.model.getOrcamentosFacade().existeOrcamento(idOrc)) {
                    System.out.print("Indique o ID do técnico responsável: ");
                    String idTecnico = scan.nextLine();
                    if (this.model.getFuncionariosFacade().validateFuncionario(idTecnico)) {
                        if (this.model.getFuncionariosFacade().getTipoFuncionario(idTecnico).equals("Tecnico")) {
                            boolean regista = true;
                            int idPlano = this.model.getPlanosFacade().getPlanos().size() + 1;
                            this.model.getPlanosFacade().adicionaPlano(idPlano, idOrc, idTecnico);
                            while (regista) {
                                System.out.print("Passo a executar: ");
                                String desc = scan.nextLine();
                                System.out.print("Custo: ");
                                float custo = scan.nextFloat();
                                System.out.print("Duração: ");
                                int val = scan.nextInt();
                                Duration prazo = Duration.parse("PT" + val + "M");
                                this.model.getPlanosFacade().atualizaPlano(idPlano, desc, custo, prazo);
                                System.out.print("Continuar? sim / não ");
                                scan.nextLine();
                                String opt = scan.nextLine();
                                regista = opt.equals("sim");
                            }
                            float custoTotal = this.model.getPlanosFacade().getPlanos().get(idPlano).getCusto();
                            Duration prazoTotal = this.model.getPlanosFacade().getPlanos().get(idPlano).getPrazo();
                            this.model.getOrcamentosFacade().atualizaOrcamento(idOrc, idTecnico, idPlano, custoTotal, prazoTotal);
                        }
                    } else System.out.print("\u001B[31mFuncionário inválido.\n\n\u001b[0m");
                } else System.out.print("\u001B[31mNão existe este orçamento no sistema!\n\n\u001b[0m");
            } catch (NumberFormatException e){ System.out.println("\u001B[31mERROR: ID tem de ser um inteiro!\n\n\u001b[0m"); }
        });
        menu.setHandlers(3, () ->  this.model.getOrcamentosFacade().consultaOrcamentos());
        menu.setHandlers(4, () ->  {
            System.out.print("Indique o ID do orçamento a remover: ");
            try {
                int id = Integer.parseInt(scan.nextLine());
                if (this.model.getOrcamentosFacade().removeOrcamento(id))
                    System.out.print("O orçamento foi removido com sucesso!\n\n");
                else System.out.print("\u001B[31mEste orçamento não existe no sistema!\n\n\u001b[0m");
            } catch (NumberFormatException e){ System.out.println("\u001B[31mERROR: ID tem de ser um inteiro!\n\n\u001b[0m"); }
        });

        menu.run();
    }

    private void gestaoDeEquipamentos() throws IOException {
        Menu menu = new Menu(new String[]{
                "Registar Equipamento",
                "Consultar Estado",
                "Entregar Equipamento",
                "Apagar Equipamento"});

        //Registar pré-condições das transições
        menu.setPreCondition(1,()->this.model.getFuncionariosFacade().getTipoFuncionario(this.model.getFuncionariosFacade().getUserAtual()).equals("Rececionista"));
        menu.setPreCondition(2,()->!this.model.getEquipamentosFacade().getEquipamentos().isEmpty());
        menu.setPreCondition(3,()->!this.model.getEquipamentosFacade().getEquipamentos().isEmpty() && this.model.getFuncionariosFacade().getTipoFuncionario(this.model.getFuncionariosFacade().getUserAtual()).equals("Rececionista"));
        menu.setPreCondition(4,()->!this.model.getEquipamentosFacade().getEquipamentos().isEmpty());

        //Registar os handlers
        menu.setHandlers(1, () -> {
            System.out.print("Indique o NIF do cliente: ");
            String lengNif = scan.nextLine();
            int nif = 0;
            if(lengNif.length() == 9){ nif = Integer.parseInt(lengNif);}
            else{ nif = verificarNif(); }
            if(!this.model.getEquipamentosFacade().existeEquipamentoNIF(nif)) {
                System.out.print("Indique o ID do equipamento: ");
                String equip = scan.nextLine();
                this.model.getEquipamentosFacade().registaEquip(nif, equip, "no armazem");
                this.model.getFuncionariosFacade().equipRecebidos(equip);
                System.out.print("O equipamento foi registado com sucesso!\n\n");
            }
            else System.out.print("\u001B[31mEste ID de equipamento não se encontra disponível!\n\n\u001b[0m");
        });
        menu.setHandlers(2, () -> {
            System.out.print("Indique o NIF do cliente: ");
            String lengNif = scan.nextLine();
            int nif = 0;
            if(lengNif.length() == 9){ nif = Integer.parseInt(lengNif);}
            else{ nif = verificarNif(); }
            if(this.model.getEquipamentosFacade().existeEquipamentoNIF(nif)) {
                String estado = this.model.getEquipamentosFacade().consultaEstado(nif);
                System.out.print("O equipamento está " + estado + "!\n\n");
            }
            else System.out.print("\u001B[31mEste equipamento não existe no sistema!\n\n\u001b[0m");
        });
        menu.setHandlers(3, () -> {
            System.out.print("Indique o NIF do cliente: ");
            String lengNif = scan.nextLine();
            int nif = 0;
            if(lengNif.length() == 9){ nif = Integer.parseInt(lengNif);}
            else{ nif = verificarNif(); }
            if(this.model.getEquipamentosFacade().existeEquipamentoNIF(nif)) {
                Equipamento equip = this.model.getEquipamentosFacade().getEquipamentos().get(nif);
                this.model.getEquipamentosFacade().levantaEquipamento(nif);
                this.model.getFuncionariosFacade().equipDevolvidos(equip);
                System.out.print("O equipamento foi levantado com sucesso!\n\n");
            }
            else System.out.print("\u001B[31mEste equipamento não existe no sistema!\n\n\u001b[0m");
        });
        menu.setHandlers(4, () -> {
            System.out.print("Indique o NIF do cliente: ");
            String lengNif = scan.nextLine();
            int nif = 0;
            if(lengNif.length() == 9){ nif = Integer.parseInt(lengNif);}
            else{ nif = verificarNif(); }
            if(this.model.getEquipamentosFacade().apagaEquipamento(nif)){
                System.out.print("O equipamento foi apagado com sucesso!\n\n");
            }
            else System.out.print("\u001B[31mEste equipamento não existe no sistema!\n\n\u001b[0m");
        });

        menu.run();
    }

    private void gestaoDeServico() throws IOException {
        Menu menu = new Menu(new String[]{
                "Serviço Expresso",
                "Serviço Programado",
                "Consultar Serviço",
                "Consultar Lista de Serviços",
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
            try {
                int id = Integer.parseInt(scan.nextLine());
                if (this.model.getServicosFacade().existeServico(id)) {
                    int idPl = this.model.getServicosFacade().getServicos().get(id).getIdPlano();
                    int idOrc = this.model.getPlanosFacade().getPlanos().get(idPl).getIdOrcamento();
                    String equipamento = this.model.getOrcamentosFacade().getOrcamentos().get(idOrc).getIdEquip();
                    String estado = null;
                    for (Equipamento e : this.model.getEquipamentosFacade().getEquipamentos().values()) {
                        if (e.getId().equals(equipamento)) {
                            estado = e.getEstado();
                            break;
                        }
                    }
                    System.out.println(this.model.getServicosFacade().consultaServico(id) + " | " + estado);
                } else System.out.print("\u001B[31mEste serviço não existe no sistema!\n\n\u001b[0m");
            } catch (NumberFormatException e){ System.out.println("\u001B[31mERROR: ID tem de ser um inteiro!\n\n\u001b[0m"); }
        });
        menu.setHandlers(4, () -> {
            for(Servico se : this.model.getServicosFacade().getServicos().values())
                System.out.println(se.toString());
            System.out.println();
        });
        menu.setHandlers(5, () ->  {
            System.out.print("Indique o ID do serviço a remover: ");
            try {
                int id = Integer.parseInt(scan.nextLine());
                if (this.model.getServicosFacade().existeServico(id)) {
                    this.model.getServicosFacade().removeServico(id);
                    System.out.print("O serviço foi removido com sucesso!\n\n");
                } else System.out.print("\u001B[31mEste serviço não existe no sistema!\n\n\u001b[0m");
            } catch (NumberFormatException e){ System.out.println("\u001B[31mERROR: ID tem de ser um inteiro!\n\n\u001b[0m"); }
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
        menu.setHandlers(1,()-> {
            System.out.print("ID do plano de trabalho correspondente: ");
            try {
                int id = Integer.parseInt(scan.nextLine());
                if (this.model.getPlanosFacade().existePlano(id)) {
                    int newID = this.model.getServicosFacade().getServicos().size() + 1;
                    int idOrc = this.model.getPlanosFacade().getPlanos().get(id).getIdOrcamento();
                    int newPlano = this.model.getPlanosFacade().getPlanos().size() + 1;
                    this.model.getPlanosFacade().adicionaPlano(newPlano, idOrc, this.model.getFuncionariosFacade().getUserAtual());
                    this.model.getServicosFacade().adicionaServicoProgramado(newID, newPlano, "programado");
                    String eqID = this.model.getOrcamentosFacade().getOrcamentos().get(idOrc).getIdEquip();
                    int nifCliente = this.model.getClientesFacade().getClienteEQ(eqID);
                    this.model.getEquipamentosFacade().atualizaEstado(nifCliente, "em espera");
                    System.out.println("ID do serviço criado: " + newID + ".\n");
                } else System.out.print("\u001B[31mID de serviço inválido.\n\n\u001b[0m");
            } catch (NumberFormatException e){ System.out.println("\u001B[31mERROR: ID tem de ser um inteiro!\n\n\u001b[0m"); }
        });
        menu.setHandlers(2,()->{
            System.out.print("ID do Serviço a alterar: ");
            try {
                int id = Integer.parseInt(scan.nextLine());
                if (this.model.getServicosFacade().existeServico(id)) {
                    int idPlano = this.model.getServicosFacade().getServicos().get(id).getIdPlano();
                    int idOrc = this.model.getPlanosFacade().getPlanos().get(idPlano).getIdOrcamento();
                    float custoMax = 1.2f * this.model.getOrcamentosFacade().getOrcamentos().get(idOrc).getCusto();
                    String eqID = this.model.getOrcamentosFacade().getOrcamentos().get(idOrc).getIdEquip();
                    int nifCliente = this.model.getClientesFacade().getClienteEQ(eqID);
                    this.model.getEquipamentosFacade().atualizaEstado(nifCliente, "em processo");
                    int newPlanoID = this.model.getPlanosFacade().adicionaPlano(idOrc,this.model.getFuncionariosFacade().getUserAtual());
                    boolean regista = true;
                    boolean concluido = false;
                    while (regista && !concluido) {
                        System.out.print("Passo a executar: ");
                        String desc = scan.nextLine();
                        System.out.print("Custo: ");
                        float custo = scan.nextFloat();
                        System.out.print("Duração: ");
                        int val = scan.nextInt();
                        Duration prazo = Duration.parse("PT" + val + "M");
                        float custoAtual = this.model.getPlanosFacade().getPlanos().get(newPlanoID).getCusto()+custo;
                        if (custoAtual <= custoMax) {
                            this.model.getPlanosFacade().atualizaPlano(newPlanoID, desc, custo, prazo);
                            System.out.print("Continuar? sim / não ");
                            scan.nextLine();
                            String opt = scan.nextLine();
                            if (!(regista = opt.equals("sim"))) {
                                System.out.print("Reparação terminada? sim / não ");
                                String end = scan.nextLine();
                                if (!(concluido = end.equals("sim"))) {
                                    this.model.getEquipamentosFacade().atualizaEstado(nifCliente, "em espera");
                                    break;
                                } else {
                                    this.model.getEquipamentosFacade().atualizaEstado(nifCliente, "reparado");
                                }
                            }
                        } else {
                            System.out.println("Valor de reparação é superior a 120% do valor orçamentado. Contactar cliente.\n");
                            this.model.getEquipamentosFacade().atualizaEstado(nifCliente, "em espera");
                            break;
                        }
                    }
                    float custoAtualizado = this.model.getPlanosFacade().getPlanos().get(newPlanoID).getCusto();
                    Duration d = this.model.getPlanosFacade().getPlanos().get(newPlanoID).getPrazo();
                    this.model.getServicosFacade().atualizaValores(id, custoAtualizado, d);
                } else System.out.print("\u001B[31mID de serviço inválido.\n\n\u001b[0m");
            } catch (NumberFormatException e){ System.out.println("\u001B[31mERROR: ID tem de ser um inteiro!\n\n\u001b[0m"); }
        });

        menu.run();
    }

    private void gestaoDeEstatisticas() throws IOException {
        Menu menu = new Menu(new String[]{
                "Relatório de serviços dos Técnicos",
                "Relatório detalhado dos Técnicos",
                "Relatório de Rececionistas"});

        //Registar os handlers
        menu.setHandlers(1,()->{
            Estatisticas e = new Estatisticas(this.model);
            System.out.println(e.reportTecnico());
        });
        menu.setHandlers(2,()->{
            Estatisticas e = new Estatisticas(this.model);
            System.out.println(e.reportDetalhado());
        });
        menu.setHandlers(3,()->{
            Estatisticas e = new Estatisticas(this.model);
            System.out.println(e.reportRececionista());
        });
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
                System.out.println("\u001B[31mDados Inválidos, tente novamente.\n" + "Tentativas restantes: " + (3 - tentativas) + "\u001b[0m");
            }
        }
        return sucesso;
    }

    public int verificarNrTelemovel() throws IOException {
        boolean sucesso = false;
        String telemovel = null;
            while(!sucesso){
                try{
                    System.out.print("\u001B[31mNúmero de telemóvel tem de ter 9 dígitos! Tente Novamente. \n\u001b[0m");
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

    public int verificarNif() throws IOException {
        boolean sucesso = false;
        String nif = null;
        while(!sucesso){
            try{
                System.out.print("\u001B[31mNIF tem de ter 9 dígitos! Tente Novamente. \n\u001b[0m");
                System.out.print("Indique o NIF do cliente: ");
                nif = scan.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(e);
            }
            if(nif.length() == 9){
                sucesso = true;
            }
        }
        return Integer.parseInt(nif);
    }

    public void ExitScreen(boolean login){
        Menu.Logo();
        if(!login)
            System.out.println("\u001B[31m\nNúmero máximo de tentativas excedido.\u001b[0m");
    }
}
