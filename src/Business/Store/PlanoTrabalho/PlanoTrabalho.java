package Business.Store.PlanoTrabalho;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.time.Duration;

public class PlanoTrabalho {
    private int idPlano;
    private String idOrcamento;
    private String idTecnico;
    private float custo;
    private Duration prazo;
    private List<Passo> passos;

    public PlanoTrabalho(){
        this.idPlano = 0;
        this.idOrcamento = null;
        this.idTecnico = null;
        this.custo = 0;
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
    public PlanoTrabalho(int idPlano, String idOrcamento, String idTecnico){
        this.idPlano = idPlano;
        this.idOrcamento = idOrcamento;
        this.idTecnico = idTecnico;
        this.custo = 0;
        this.prazo = Duration.ZERO;
        this.passos = new ArrayList<>();
    }

    public int getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(int idPlano) {
        this.idPlano = idPlano;
    }

    public String getIdOrcamento() {
        return idOrcamento;
    }

    public void setIdOrcamento(String idOrcamento) {
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
        return idPlano == that.idPlano && Float.compare(that.custo, custo) == 0 && Objects.equals(idOrcamento, that.idOrcamento) && Objects.equals(idTecnico, that.idTecnico) && Objects.equals(prazo, that.prazo) && Objects.equals(passos, that.passos);
    }

    public PlanoTrabalho clone(){
        return new PlanoTrabalho(this);
    }
}