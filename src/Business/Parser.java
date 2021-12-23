package Business;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Business.Store.Funcionario.Funcionario;
import Business.Store.StoreLNFacade;

public class Parser {

    public static StoreLNFacade parse() throws IOException {

        List<String> lines = readFile("input/authetication.txt");
        //List<String> lines1 = readFile("input/equipments.txt");
        Map<String,Funcionario> loginData = new HashMap<>(); // username, funcionario
        //Map<String,Equipamento> equipamentos = new HashMap<>(); // username, equipamento
        String[] tokens; // para as credenciais
        //String[] tokens1; // para os equipamentos

        for (String l : lines) {
            tokens = l.split(";", 3);
            Funcionario func = new Funcionario(tokens[0],tokens[1],tokens[2]);
            loginData.put(tokens[0],func);
        }
        StoreLNFacade data = new StoreLNFacade(true,loginData/*,equipamentos*/);
        return data;
    }

    public static List<String> readFile(String file) {
        List<String> lines;
        try { lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8); }
        catch(IOException exc) { lines = new ArrayList<>(); }
        return lines;
    }
}

