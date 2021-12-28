package Business;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        ClientesFacade dataCliente = new ClientesFacade(clientes,contactados,naoContactados);
        return dataCliente;
    }

    public static FuncionariosFacade parseLogin() throws IOException {
        List<String> lines = readFile("input/authetication.txt");
        Map<String,Funcionario> loginData = new HashMap<>();       // username do funcionario, funcionario
        String[] tokens;

        for (String l : lines) {
            tokens = l.split(";", 3);
            Funcionario func = new Funcionario(tokens[0],tokens[1],tokens[2]);
            loginData.put(tokens[0],func.clone());
        }
        FuncionariosFacade dataFunc = new FuncionariosFacade(loginData);
        return dataFunc;
    }

    public static EquipamentosFacade parseEquip() throws IOException {
        List<String> lines = readFile("input/dadosEquipamentos.txt");
        Map<Integer,Equipamento> equipamentos = new HashMap<>(); // nif do cliente, equipamento
        String[] tokens;

        for (String l : lines) {
            tokens = l.split(";", 3);
            Equipamento equip = new Equipamento(tokens[1],tokens[2]);
            equipamentos.put(Integer.parseInt(tokens[0]),equip.clone());
        }
        EquipamentosFacade dataEquip = new EquipamentosFacade(equipamentos);
        return dataEquip;
    }

    public static OrcamentosFacade parseOrcamento() throws IOException {
        List<String> lines = readFile("input/dadosOrcamento.txt");
        Map<Integer, Orcamento> orcamentos = new HashMap<>();       // id do equipamento, orçamento
        String[] tokens;
        DateTimeFormatter dataformatada = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        for (String l : lines) {
            tokens = l.split(";", 8);
            Orcamento orc = new Orcamento(Integer.parseInt(tokens[0]),tokens[1],LocalDateTime.parse(tokens[2],dataformatada),Float.parseFloat(tokens[3]),Integer.parseInt(tokens[4]),tokens[5],tokens[6],tokens[7]);
            orcamentos.put(Integer.parseInt(tokens[0]),orc.clone());
        }
        OrcamentosFacade dataOrc = new OrcamentosFacade(orcamentos);
        return dataOrc;
    }

    public static List<String> readFile(String file) {
        List<String> lines;
        try { lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8); }
        catch(IOException exc) { lines = new ArrayList<>(); }
        return lines;
    }
}

