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
import Business.Store.Funcionario.Funcionario;
import Business.Store.StoreLNFacade;

public class Parser {

    public static StoreLNFacade parse() throws IOException {

        List<String> lines = readFile("input/authetication.txt");
        List<String> lines1 = readFile("input/dadosClientes.txt");
        //List<String> lines2 = readFile("input/dadosEquipamentos.txt");
        Map<String,Funcionario> loginData = new HashMap<>();       // username do funcionario, funcionario
        Map<String,Cliente> clientes = new HashMap<>();            // id do cliente, cliente
        //Map<Integer,Equipamento> equipamentos = new HashMap<>(); // nif do cliente, equipamento
        String[] tokens; // para as credenciais
        String[] tokens1; // para os clientes
        //String[] tokens2; // para os equipamentos

        for (String l : lines) {
            tokens = l.split(";", 3);
            Funcionario func = new Funcionario(tokens[0],tokens[1],tokens[2]);
            loginData.put(tokens[0],func.clone());
        }
        for (String l : lines1) {
            tokens1 = l.split(";", 6);
            Cliente cliente = new Cliente(tokens1[0],tokens1[1],Integer.parseInt(tokens1[2]),Integer.parseInt(tokens1[3]),tokens1[4],tokens1[5]);
            clientes.put(tokens1[0],cliente.clone());
        }
        StoreLNFacade data = new StoreLNFacade(true,loginData,clientes/*,equipamentos*/);
        return data;
    }

    public static List<String> readFile(String file) {
        List<String> lines;
        try { lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8); }
        catch(IOException exc) { lines = new ArrayList<>(); }
        return lines;
    }
}

