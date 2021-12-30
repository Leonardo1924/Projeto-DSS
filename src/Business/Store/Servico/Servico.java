package Business.Store.Servico;

import java.io.PrintWriter;
import java.time.Duration;
import java.util.Objects;

public class Servico {
    private int id;
    private String tipo;       // expresso ; programado
    private int idPlano;
    private float custoTotal;
    private Duration tempoTotal;

    public Servico() {
        this.id = -1;
        this.tipo = "";
        this.idPlano = 0;
        this.custoTotal = -1;
        this.tempoTotal = Duration.ZERO;
    }

    public Servico(int id, String tipo, int idPlano, float custoTotal, Duration tempoTotal) {
        this.id = id;
        this.tipo = tipo;
        this.idPlano = idPlano;
        this.custoTotal = custoTotal;
        this.tempoTotal = tempoTotal;
    }

    public Servico(Servico servico) {
        this.id = servico.getId();
        this.tipo = servico.getTipo();
        this.idPlano = servico.getIdPlano();
        this.custoTotal = servico.getCustoTotal();
        this.tempoTotal = servico.getTempoTotal();
    }

    public Servico(int id, float custo, Duration tempo, String expresso) {
        this.id = id;
        this.tipo = expresso;
        this.idPlano = 0;
        this.custoTotal = custo;
        this.tempoTotal = tempo;
    }

    public Servico(int newID, int newPlano, String programado) {
        this.id = newID;
        this.tipo = programado;
        this.idPlano = newPlano;
        this.custoTotal = 0;
        this.tempoTotal = Duration.ZERO;
    }

    public int getId() {
        return this.id;
    }

    public String getTipo() {
        return this.tipo;
    }

    public int getIdPlano() {
        return this.idPlano;
    }

    public float getCustoTotal() {
        return this.custoTotal;
    }

    public Duration getTempoTotal() {
        return this.tempoTotal;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setIdPlano(int idPlano) {
        this.idPlano = idPlano;
    }

    public void setCustoTotal(float custoTotal) {
        this.custoTotal = custoTotal;
    }

    public void setTempoTotal(Duration tempoTotal) {
        this.tempoTotal = tempoTotal;
    }

    public Servico clone(){
        return new Servico(this);
    }

    public String toString(){
        String idP = "n/a";
        if (this.idPlano > 0) idP = String.valueOf(this.idPlano);
        return "\033[1;35mId: \033[0m" + this.id +
                " \033[1;35mTipo: \033[0m" + this.tipo +
                " \033[1;35mId Plano: \033[0m" + idP +
                " \033[1;35mCusto Total: \033[0m" + this.custoTotal +
                " \033[1;35mTempo Total: \033[0m" + this.tempoTotal;
    }

    public void gravar(PrintWriter print){
        print.println(this.id + ";" + this.tipo + ";" + this.idPlano + ";" + this.custoTotal + ";" + this.tempoTotal);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Servico servico = (Servico) o;
        return id == servico.id && idPlano == servico.idPlano && Float.compare(servico.custoTotal, custoTotal) == 0 && Objects.equals(tipo, servico.tipo) && Objects.equals(tempoTotal, servico.tempoTotal);
    }

}
