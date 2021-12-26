package Business.Store.Funcionario;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Funcionario{

    private String username;
    private String password;
    private String tipo;

    public Funcionario(){
        this.username = "";
        this.password = "";
        this.tipo = "";
    }

    public Funcionario(String username, String password, String tipo){
        this.username = username;
        this.password = password;
        this.tipo = tipo;
    }

    public Funcionario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Funcionario(Funcionario umFunc){
        this.username = umFunc.getUsername();
        this.password = umFunc.getPassword();
        this.tipo = umFunc.getTipo();
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

    public Funcionario clone(){
        return new Funcionario(this);
    }
}
