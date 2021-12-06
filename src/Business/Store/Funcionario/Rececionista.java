package Business.Store.Funcionario;

// import Business.Store.Gestor.Gestor;

public class Rececionista extends Funcionario{

    public Rececionista(){
        super();
    }

    public Rececionista(String username, String password){
        super(username,password);
    }

    public Rececionista(Tecnico tecnico){
        super(tecnico);
    }

    public Tecnico clone(){
        return new Rececionista(this);
    }

    // Método que regista a entrega do equipamento pelo cliente e o pedido de orçamento
    // public boolean equipmentReceived(int NIF){
    // return true;
    // }

    // Método que contacta um cliente via email
    // public boolean contactClient(String email){
    // return true;
    // }


}
