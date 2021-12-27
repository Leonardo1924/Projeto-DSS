package Business.Store.Servico;

import Business.Store.Cliente.Cliente;

public class Servico {
    private String id;
    private String tipo;       // expresso ; normal
    private float custoTotal;
    private float tempoTotal;

    public Servico() {
        this.id = "";
        this.tipo = "";
        this.custoTotal = 0;
        this.tempoTotal = 0;
    }

    public Servico(String id, String tipo, float custoTotal, float tempoTotal) {
        this.id = id;
        this.tipo = tipo;
        this.custoTotal = custoTotal;
        this.tempoTotal = tempoTotal;
    }

    public Servico(Servico servico) {
        this.id = servico.getId();
        this.tipo = servico.getTipo();
        this.custoTotal = servico.getCustoTotal();
        this.tempoTotal = servico.getTempoTotal();
    }

    public String getId() {
        return this.id;
    }

    public String getTipo() {
        return this.tipo;
    }

    public float getCustoTotal() {
        return this.custoTotal;
    }

    public float getTempoTotal() {
        return this.tempoTotal;
    }

    public Servico clone(){
        return new Servico(this);
    }

    public String toString(){
        return "\033[1;35mId: \033[0m" + this.id +
                " \033[1;35mTipo: \033[0m" + this.tipo +
                " \033[1;35mCusto Total: \033[0m" + this.custoTotal +
                " \033[1;35mTempo Total: \033[0m" + this.tempoTotal;
    }

    public boolean equals(Object obj){
        if (obj == this) return true;
        if (obj == null || obj.getClass().equals(this.getClass())) return false;
        Servico servico = (Servico) obj;
        return this.id.equals(servico.getId()) &&
                this.tipo.equals(servico.getTipo()) &&
                this.custoTotal == servico.getCustoTotal() &&
                this.tempoTotal == servico.getTempoTotal();
    }
}
