package Business;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Data.Data;

public class SGRE {

    public static Data parse() throws IOException {
        List<String> lines = readFile("./input/authentication.csv");
        Map<String,String> loginData = new HashMap<>(); // username, password
        String[] oneLine;

        for (String l : lines) {
            oneLine = l.split(";", 2);
            loginData.put(oneLine[0],oneLine[1]);
        }
        Data data = new Data(loginData);
        return data;
    }

    public static List<String> readFile(String file) {
        List<String> lines;
        try { lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8); }
        catch(IOException exc) { lines = new ArrayList<>(); }
        return lines;
    }
}

