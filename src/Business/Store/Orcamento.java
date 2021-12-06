package Business.Store;


public class Orcamento {
    private String id;
    private LocalDateTime data;
    private int custo;
    private Tecnico IdTecnico;

    public Orcamento(){
        this.id = "";
        this.data = LocalDatetime.now();
        this.custo = 0;
        this.IdTecnico = new Tecnico();
    }

    public Orcamento(String id, LocalDateTime data, int custo, Tecnico IdTecnico){
        this.id = id;
        this.data = LocalDatetime.now();
        this.custo = 0;
        this.IdTecnico = IdTecnico;
    }

    public Orcamento(Orcamento orc){
        this.id = orc.getId();
        this.data = orc.getData();
        this.custo = orc.getCusto();
        this.IdTecnico = orc.getIdtecnico();
    }

    public LocalDateTime getData(){
        return this.data;
    }

    public String getId(){
        return this.id;
    }

    public int getCusto(){
        return this.custo;
    }

    public String getIdTecnico(){
        return this.IdTecnico;
    }

    public void setData(LocalDateTime data){
        this.data = data;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setCusto(int custo){
        this.custo = custo;
    }

    public void setIdTecnico(String IdTecnico){
        this.IdTecnico = IdTecnico;
    }

    public Orcamento clone(){
        return new Orcamento(this);
    }

    public boolean equals(Object obj){
        if (obj == this) return true;
        if (objb == null || obj.getClass().equals(this.getClass())) return false;
        Orcamento orcamento = (Orcamento) obj;
        return this.id.equals(orcamento.getId()) && 
                this.data == orcamento.getData() &&
                this.custo == orcamento.getCusto() &&
                this.IdTecnico.equals(orcamento.getIdtecnico());
    }
}
