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
import Data.SGRE;

public class Parser {

    public static SGRE parse() throws IOException {

        List<String> lines = readFile("input/authetication.txt");
        Map<String,Funcionario> loginData = new HashMap<>(); // username, password
        String[] oneLine;

        for (String l : lines) {
            oneLine = l.split(";", 3);
            Funcionario func = new Funcionario(oneLine[0],oneLine[1],oneLine[2]);
            loginData.put(oneLine[0],func);
        }
        SGRE data = new SGRE(loginData);
        return data;
    }

    public static List<String> readFile(String file) {
        List<String> lines;
        try { lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8); }
        catch(IOException exc) { lines = new ArrayList<>(); }
        return lines;
    }
}

