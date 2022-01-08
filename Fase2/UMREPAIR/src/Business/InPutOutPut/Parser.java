package Business.InPutOutPut;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Business.Store.Cliente.Cliente;
import Business.Store.Cliente.ClientesFacade;
import Business.Store.Equipamento.EquipamentosFacade;
import Business.Store.Funcionario.Funcionario;
import Business.Store.Funcionario.FuncionariosFacade;
import Business.Store.Equipamento.Equipamento;
import Business.Store.Orcamento.Orcamento;
import Business.Store.Orcamento.OrcamentosFacade;
import Business.Store.PlanoTrabalho.Passo;
import Business.Store.PlanoTrabalho.PlanoFacade;
import Business.Store.PlanoTrabalho.PlanoTrabalho;
import Business.Store.Servico.Servico;
import Business.Store.Servico.ServicosFacade;

public class Parser {

    public static ClientesFacade parseCliente() throws IOException {
        List<String> lines = readFile("input/dadosClientes.txt");
        Map<String,Cliente> clientes = new HashMap<>();  // id do cliente, cliente
        List<String> contactados = new ArrayList<>();
        List<String> naoContactados = new ArrayList<>();
        String[] tokens;

        for (String l : lines) {
            tokens = l.split(";", 6);
            Cliente cliente = new Cliente(tokens[0],tokens[1],Integer.parseInt(tokens[2]),Integer.parseInt(tokens[3]),tokens[4],tokens[5]);
            clientes.put(tokens[0],cliente.clone());
        }
        naoContactados = new ArrayList<>(clientes.keySet());
        return new ClientesFacade(clientes,contactados,naoContactados);
    }

    public static FuncionariosFacade parseLogin() throws IOException {
        List<String> lines = readFile("input/dadosFuncionarios.txt");
        Map<String,Funcionario> loginData = new HashMap<>();       // username do funcionario, funcionario
        String[] tokens;

        for (String l : lines) {
            tokens = l.split(";", 5);
            List<String> listRecebidos = new ArrayList<>(Arrays.asList(tokens[3].split(",")));
            List<String> listDevolvidos = new ArrayList<>(Arrays.asList(tokens[4].split(",")));
            if(listRecebidos.size() != 1 || !listRecebidos.get(0).equals("n/a")) {
                if (listDevolvidos.size() != 1 || !listDevolvidos.get(0).equals("n/a")) {
                    Funcionario func = new Funcionario(tokens[0], tokens[1], tokens[2], listRecebidos, listDevolvidos);
                    loginData.put(tokens[0], func.clone());
                } else {
                    listDevolvidos = new ArrayList<>();
                    Funcionario func = new Funcionario(tokens[0], tokens[1], tokens[2], listRecebidos, listDevolvidos);
                    loginData.put(tokens[0], func.clone());
                }
            }
            else{
                listRecebidos = new ArrayList<>();
                listDevolvidos = new ArrayList<>();
                Funcionario func = new Funcionario(tokens[0], tokens[1], tokens[2], listRecebidos, listDevolvidos);
                loginData.put(tokens[0], func.clone());
            }
        }
        return new FuncionariosFacade(loginData);
    }

    public static EquipamentosFacade parseEquip() throws IOException {
        List<String> lines = readFile("input/dadosEquipamentos.txt");
        Map<Integer,Equipamento> equipamentos = new TreeMap<>(); // nif do cliente, equipamento
        String[] tokens;

        for (String l : lines) {
            tokens = l.split(";", 3);
            Equipamento equip = new Equipamento(tokens[1],tokens[2]);
            equipamentos.put(Integer.parseInt(tokens[0]),equip.clone());
        }
        return new EquipamentosFacade(equipamentos);
    }

    public static OrcamentosFacade parseOrcamento() throws IOException {
        Scanner scan = new Scanner(System.in);
        List<String> lines = readFile("input/dadosOrcamento.txt");
        Map<Integer, Orcamento> orcamentos = new TreeMap<>();       // id do orçamento, orçamento
        String[] tokens;
        DateTimeFormatter dataformatada = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        for (String l : lines) {
            tokens = l.split(";", 9);

            Orcamento orc = new Orcamento(Integer.parseInt(tokens[0]),tokens[1],LocalDateTime.parse(tokens[2],dataformatada),
                    Float.parseFloat(tokens[3]),Duration.parse(tokens[4]),tokens[5],tokens[6],Integer.parseInt(tokens[7]),Boolean.parseBoolean(tokens[8]));
            orcamentos.put(Integer.parseInt(tokens[0]),orc.clone());
        }
        return new OrcamentosFacade(orcamentos);
    }

    public static ServicosFacade parseServico() throws IOException {
        List<String> lines = readFile("input/dadosServico.txt");
        Map<Integer, Servico> servicos = new TreeMap<>();       // id do serviço, serviço
        String[] tokens;

        for (String l : lines) {
            tokens = l.split(";", 5);
            Servico ser = new Servico(Integer.parseInt(tokens[0]),tokens[1],Integer.parseInt(tokens[2]),Float.parseFloat(tokens[3]),Duration.parse(tokens[4]));
            servicos.put(Integer.parseInt(tokens[0]),ser.clone());
        }
        return new ServicosFacade(servicos);
    }

    public static PlanoFacade parsePlano() throws IOException {
        List<String> lines = readFile("input/dadosPlano.txt");
        Map<Integer, PlanoTrabalho> planos = new TreeMap<>();       // id do plano, plano de trabalho
        String[] tokens;

        for (String l : lines) {
            tokens = l.split(";", 6);
            List<String> list = new ArrayList<String>(Arrays.asList(tokens[5].split("@")));
            List<Passo> passos = new ArrayList<>();
            if(list.size() != 1 || !list.get(0).equals("n/a")) {
                for(String p : list) {
                    String[] stringPasso = p.split(",");
                    Passo passo = new Passo(stringPasso[0],Float.parseFloat(stringPasso[1]),Duration.parse(stringPasso[2]));
                    passos.add(passo.clone());
                }
            }
            PlanoTrabalho pt = new PlanoTrabalho(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),tokens[2],Float.parseFloat(tokens[3]),Duration.parse(tokens[4]),passos);
            planos.put(Integer.parseInt(tokens[0]),pt.clone());
        }
        return new PlanoFacade(planos);
    }

    public static List<String> readFile(String file) {
        List<String> lines;
        try { lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8); }
        catch(IOException exc) { lines = new ArrayList<>(); }
        return lines;
    }
}

