package Business.Store.PlanoTrabalho;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;
import java.time.Duration;

public class PlanoTrabalho {
    private String idPlano;
    private String idOrcamento;
    private String idTecnico;
    private float custo;
    private int prazo;
    private List<Passo> passos;

    public PlanoTrabalho(){
        this.idPlano = null;
        this.idOrcamento = null;
        this.idTecnico = null;
        this.custo = 0;
        this.prazo = 0;
        this.passos = null;
    }

    void createPlano(Integer idOrc, String idTecnico){
    };

    public String getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(String idPlano) {
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

    public int getPrazo() {
        return prazo;
    }

    public void setPrazo(int prazo) {
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
        return Float.compare(that.custo, custo) == 0 && prazo == that.prazo && Objects.equals(idPlano, that.idPlano) && Objects.equals(idOrcamento, that.idOrcamento) && Objects.equals(idTecnico, that.idTecnico) && Objects.equals(passos, that.passos);
    }



    /*
    private List<Passo> passos;

    public PlanoTrabalho(){
        this.passos = new ArrayList<Passo>();
    }

    public PlanoTrabalho(List<Passo> passos){
        this.passos = new ArrayList<>();
        for(Passo p : passos) {
            this.passos.add(p);
        }
    }

    public PlanoTrabalho(PlanoTrabalho plano){
        this.passos = getPassos();
    }

    public List<Passo> getPassos(){
        return this.passos.stream().map(Passo::clone).collect(Collectors.toList());;
    }

    public void setPassos(List<Passo> passos){
        this.passos = new ArrayList<>();
        for(Passo p : passos) {
            this.passos.add(p);
        }
    }

    public float custoTotalPlano(){
        float res = 0;
        for(Passo p: this.passos){
            res += p.getCustoPasso();
        }
        return res;
    }

    public Duration tempoTotalPlano(){
        Duration res = Duration.ofMinutes(0);
        for(Passo p: this.passos){
            res.plus(Duration.ofMinutes(p.getTempoPasso()));
        }
        return res;
    }

    public PlanoTrabalho clone(){
        return new PlanoTrabalho(this);
    }

     */
}