package Business.Store.PlanoTrabalho;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.time.Duration;

public class PlanoTrabalho {
    private int idPlano;
    private int idOrcamento;
    private String idTecnico;
    private float custo;
    private Duration prazo;
    private List<Passo> passos;

    public PlanoTrabalho(){
        this.idPlano = -1;
        this.idOrcamento = -1;
        this.idTecnico = null;
        this.custo = -1;
        this.prazo = Duration.ZERO;
        this.passos = new ArrayList<>();
    }

    public PlanoTrabalho(PlanoTrabalho pt){
        this.idPlano = pt.getIdPlano();
        this.idOrcamento = pt.getIdOrcamento();
        this.idTecnico = pt.getIdTecnico();
        this.custo = pt.getCusto();
        this.prazo = pt.getPrazo();
        this.passos = new ArrayList<>(pt.getPassos());
    }

    public PlanoTrabalho(int idPlano, int idOrcamento, String idTecnico, float custo, Duration prazo){
        this.idPlano = idPlano;
        this.idOrcamento = idOrcamento;
        this.idTecnico = idTecnico;
        this.custo = custo;
        this.prazo = prazo;
        this.passos = new ArrayList<>();
    }

    public int getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(int idPlano) {
        this.idPlano = idPlano;
    }

    public int getIdOrcamento() {
        return idOrcamento;
    }

    public void setIdOrcamento(int idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    public String getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(String idTecnico) {
        this.idTecnico = idTecnico;
    }

    public float getCusto() {
        return custo;
    }

    public void setCusto(float custo) {
        this.custo = custo;
    }

    public Duration getPrazo() {
        return prazo;
    }

    public void setPrazo(Duration prazo) {
        this.prazo = prazo;
    }

    public List<Passo> getPassos() {
        return passos;
    }

    public void setPassos(List<Passo> passos) {
        this.passos = passos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanoTrabalho that = (PlanoTrabalho) o;
        return idPlano == that.idPlano && idOrcamento == that.idOrcamento && Float.compare(that.custo, custo) == 0 && Objects.equals(idTecnico, that.idTecnico) && Objects.equals(prazo, that.prazo) && Objects.equals(passos, that.passos);
    }

    public PlanoTrabalho clone(){
        return new PlanoTrabalho(this);
    }

    public String toString(){
        return "\033[1;35mID plano: \033[0m" + idPlano +
                " \033[1;35mID orçamento: \033[0m" + idOrcamento +
                " \033[1;35mID técnico: \033[0m" + idTecnico +
                " \033[1;35m Custo: \033[0m" + custo +
                " \033[1;35mPrazo: \033[0m" + prazo +
                " \033[1;35mPassos: \033[0m" + passos;
    }
}