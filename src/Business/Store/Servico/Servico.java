package Business.Store.Servico;

import Business.Store.Cliente.Cliente;

import java.io.PrintWriter;
import java.time.Duration;
import java.util.Objects;

public class Servico {
    private String id;
    private String tipo;       // expresso ; normal
    private String idPlano;
    private float custoTotal;
    private Duration tempoTotal;

    public Servico() {
        this.id = "";
        this.tipo = "";
        this.idPlano = "";
        this.custoTotal = -1;
        this.tempoTotal = Duration.ZERO;
    }

    public Servico(String id, String tipo, String idPlano, float custoTotal, Duration tempoTotal) {
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
        this.id = String.valueOf(id);
        this.tipo = expresso;
        this.idPlano = "0";
        this.custoTotal = custo;
        this.tempoTotal = tempo;
    }

    public String getId() {
        return this.id;
    }

    public String getTipo() {
        return this.tipo;
    }

    public String getIdPlano() {
        return this.idPlano;
    }

    public float getCustoTotal() {
        return this.custoTotal;
    }

    public Duration getTempoTotal() {
        return this.tempoTotal;
    }

    public Servico clone(){
        return new Servico(this);
    }

    public String toString(){
        if (this.idPlano.equals("0")) this.idPlano = "n/a";
        return "\033[1;35mId: \033[0m" + this.id +
                " \033[1;35mTipo: \033[0m" + this.tipo +
                " \033[1;35mId Plano: \033[0m" + this.idPlano +
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
        return Float.compare(servico.custoTotal, custoTotal) == 0 && Objects.equals(id, servico.id) && Objects.equals(tipo, servico.tipo) && Objects.equals(idPlano, servico.idPlano) && Objects.equals(tempoTotal, servico.tempoTotal);
    }

}
