package Business.Store.Técnico;

import Business.Store.Gestor.Gestor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Técnico {
    String username;
    String password;

    public Técnico(String username, String password){
        this.username = username;
        this.password = password;
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
        return this.password.equals(Gestor.gerar(password));
    }
}
