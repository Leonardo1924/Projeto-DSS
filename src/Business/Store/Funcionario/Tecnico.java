package Business.Store.Funcionario;

// import Business.Store.Gestor.Gestor;

public class Tecnico extends Funcionario{

    public Tecnico(){
        super();
    }

    public Tecnico(String username, String password){
        super(username,password);
    }

    public Tecnico(Tecnico tecnico){
        super(tecnico);
    }

    public Tecnico clone(){
        return new Tecnico(this);
    }

    /*
    private String username;
    private String password;

    public Tecnico(String username, String password){
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
    */
}
