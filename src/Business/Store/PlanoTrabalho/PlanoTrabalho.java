package Business.Store.PlanoTrabalho;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.time.Duration;

public class PlanoTrabalho {
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