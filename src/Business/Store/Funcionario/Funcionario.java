package Business.Store.Funcionario;

// import Business.Store.Gestor.Gestor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Funcionario{
    private String username;
    private String password;

    public Funcionario(){

    }
    public Funcionario(String username, String password){
            this.username = username;;
            this.password = password;
        }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public static String gerar(final String password){
        try{
            byte[] hash = MessageDigest.getInstance("SHA-256").digest((password).getBytes());
            return Base64.getEncoder().encodeToString(hash);
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean rightpassWord(String password){
        // return this.password.equals(Business.Store.Gestor.Gestor.gerar(password));
        return this.password.equals(gerar(password));
    }
}
