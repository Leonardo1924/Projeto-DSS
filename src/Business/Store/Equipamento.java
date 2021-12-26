package Business.Store;

public class Equipamento {
    private String id;

    public Equipamento(){
        this.id = "";
    }

    public Equipamento(String Id){
        this.id = Id;
    }

    public Equipamento(Equipamento equipamento){
        this.id = equipamento.getId();
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    @Override
    public Equipamento clone(){
        return new Equipamento(this);
    }

    public String toString(){
        return "Equipamento{" + 
                "Id: " + id +
                '}';
    }

    public boolean equals(Object obj){
        if (obj == this) return true;
        if (obj == null || obj.getClass().equals(this.getClass())) return false;
        Equipamento equipamento = (Equipamento) obj;
        return this.id.equals(equipamento.getId());
    }
}
