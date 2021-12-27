package Business;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Business.Cliente.Cliente;
import Business.Cliente.ClientesFacade;
import Business.Store.Equipamento.EquipamentosFacade;
import Business.Store.Funcionario.Funcionario;
import Business.Store.Funcionario.FuncionariosFacade;
import Business.Store.Equipamento.Equipamento;

public class Parser {

    public static ClientesFacade parseCliente() throws IOException {
        List<String> lines = readFile("input/dadosClientes.txt");
        Map<String,Cliente> clientes = new HashMap<>();  // id do cliente, cliente
        String[] tokens;

        for (String l : lines) {
            tokens = l.split(";", 6);
            Cliente cliente = new Cliente(tokens[0],tokens[1],Integer.parseInt(tokens[2]),Integer.parseInt(tokens[3]),tokens[4],tokens[5]);
            clientes.put(tokens[0],cliente.clone());
        }
        ClientesFacade dataCliente = new ClientesFacade(clientes);
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

    public static List<String> readFile(String file) {
        List<String> lines;
        try { lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8); }
        catch(IOException exc) { lines = new ArrayList<>(); }
        return lines;
    }
}

