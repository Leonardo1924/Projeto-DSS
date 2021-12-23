package Data;

import Business.Store.Funcionario.Funcionario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

/**
 * Classe do objeto cujo propósito é guardar toda a informação.
 *
 * @author grupo 3
 * @version (número de versão ou data)
 */
public class SGRE {
    private Map<String,Funcionario> loginData; // username, password

    /**
     * Construtor por omissão
     */
    public SGRE(){
        this.loginData = new HashMap<>();
    }

    /**
     * Construtor de um objeto data com parametros
     * @param loginData todos os usernames e passwords do sistema
     */
    public SGRE(Map<String, Funcionario> loginData) {
        this.loginData = loginData.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e-> e.getValue()));
    }

    /**
     * Método que obtém todas as equipas.
     * @return todos os usernames e passwords do sistema
     */
    public Map<String,Funcionario> getLoginData(){
        return this.loginData.entrySet().stream().collect(Collectors.toMap(par->par.getKey(), par->par.getValue()));
    }

    /**
     * Método que obtém apenas uma equipa.
     * @param username o username.
     * @return a password corresponde a username
     */
    public String getPassword(String username){
        return this.loginData.get(username).getPassword();
    }

    /**
     * Método que muda as equipas no objeto
     * @param username o username.
     * @param newPassword a nova password.
     */
    public void changePassword(String username,String newPassword){
        String tipo = loginData.get(username).getTipo();
        Funcionario func = new Funcionario(username,newPassword,tipo);
        this.loginData.put(username,func);
    }
    /**
     * Método que insere uma equipa na lista de equipas
     * @param username o username a adicionar
     * @param password a password a adicionar
     * @param tipo o tipo do funcionário - rececionista, técnico ou gestor
     */
    public void createAccount(String username, String password, String tipo){
        Funcionario func = new Funcionario(username,password,tipo);
        loginData.put(username,func);
    }
}
