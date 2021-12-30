package Business.Store.Funcionario;

import Business.Store.PlanoTrabalho.Passo;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Funcionario{

    private String username;
    private String password;
    private String tipo;
    private List<String> equipamentosRecebidos;
    private List<String> equipamentosDevolvidos;

    public Funcionario(String username, String password, String tipo, List<String> equipamentosRecebidos, List<String> equipamentosDevolvidos){
        this.username = username;
        this.password = password;
        this.tipo = tipo;
        this.equipamentosRecebidos = new ArrayList<>(equipamentosRecebidos);
        this.equipamentosDevolvidos = new ArrayList<>(equipamentosDevolvidos);
    }

    public Funcionario(Funcionario umFunc){
        this.username = umFunc.getUsername();
        this.password = umFunc.getPassword();
        this.tipo = umFunc.getTipo();
        this.equipamentosRecebidos = umFunc.getEquipamentosRecebidos();
        this.equipamentosDevolvidos = umFunc.getEquipamentosDevolvidos();
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public String getTipo(){
        return this.tipo;
    }

    public List<String> getEquipamentosRecebidos(){
        return new ArrayList<>(this.equipamentosRecebidos);
    }

    public List<String> getEquipamentosDevolvidos(){
        return new ArrayList<>(this.equipamentosDevolvidos);
    }

    public void gravar(PrintWriter print){
        String recebidos = String.join(",",this.equipamentosRecebidos);
        String devolvidos = String.join(",",this.equipamentosDevolvidos);
        print.println(this.username + ";" + this.password + ";" + this.tipo + ";" + recebidos + ";" + devolvidos);
    }

    public Funcionario clone(){
        return new Funcionario(this);
    }
}
