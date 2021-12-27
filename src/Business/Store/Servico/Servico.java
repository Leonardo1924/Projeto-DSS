package Business.Store.Servico;

public class Servico {
    private String id;
    private float custoTotal;
    private int tempoTotal;

    public Servico() {
        this.id = "";
        this.custoTotal = 0;
        this.tempoTotal = 0;
    }

    public Servico(String id, float custoTotal, int tempoTotal) {
        this.id = id;
        this.custoTotal = custoTotal;
        this.tempoTotal = tempoTotal;
    }

    public Servico(Servico servico) {
        this.id = servico.getId();
        this.custoTotal = servico.getCustoTotal();
        this.tempoTotal = servico.getTempoTotal();
    }

    public String getId() {
        return this.id;
    }

    public float getCustoTotal() {
        return this.custoTotal;
    }

    public int getTempoTotal() {
        return this.tempoTotal;
    }
/*
    public Servico clone(){
        return new Servico(this);
    }

    public String toString(){
        return "Serviço{" +
                "Id: " + this.id +
                "Custo Total: " + this.custoTotal +
                "Tempo Total: " + this.tempoTotal +
                '}';
    }

    public boolean equals(Object obj){
        if (obj == this) return true;
        if (obj == null || obj.getClass().equals(this.getClass())) return false;
        Equipamento equipamento = (Equipamento) obj;
        return this.id.equals(equipamento.getId());
    }
    */
}
