package Business.Store;

public class Equipamento {
    private String Id;

    public Equipamento(){
        this.Id = "";
    }

    public Equipamento(String Id){
        this.Id = Id;
    }

    public Equipamento(Equipamento equipamento){
        this.Id = equipamento.getId();
    }

    public String getId(){
        return this.Id;
    }

    public void setId(String Id){
        this.Id = Id;
    }

    public Equipamento clone(){
        return new Equipmento(this);
    }

    public String toString(){
        return "Equipamento{" + 
                "Id: " + Id +
                '}';
    }

    public boolean equals(Object obj){
        if (obj == this) return true;
        if (objb == null || obj.getClass().equals(this.getClass())) return false;
        Equipamento equipamento = (Equipamento) obj;
        return this.id.equals(equipamento.getId());
    }
}
