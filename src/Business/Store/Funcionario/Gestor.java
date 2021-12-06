package Business.Store.Funcionario;

public class Gestor extends Funcionario{

    public Gestor(){
        super();
    }

    public Gestor(String username, String password){
        super(username,password);
    }

    public Gestor(Tecnico tecnico){
        super(Gestor);
    }

    public Tecnico clone(){
        return new Gestor(this);
    }


    /*
    JÁ NÃO É NECESSÁRIO !!!!!!!!!!!!!!

    String username;
    String password;

    public Gestor(String username, String password){
        this.username = username;;
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
    */
}
