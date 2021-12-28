package Business.Store.PlanoTrabalho;

import java.time.Duration;
import java.util.Map;
import java.util.stream.Collectors;

public class PlanoFacade implements IGestPlano {

    private Map<Integer, PlanoTrabalho> planos;

    public PlanoFacade(Map<Integer,PlanoTrabalho> p){
        this.planos = p.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public Map<Integer, PlanoTrabalho> getPlanos() {
        return this.planos.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public int adicionaPlano(int idOrc, String idTecnico, float custo, Duration prazo){
        int idPlano = this.planos.size()+1;
        PlanoTrabalho pt = new PlanoTrabalho(idPlano,idOrc,idTecnico,custo,prazo);
        this.planos.put(idPlano,pt.clone());
        return idPlano;
    }


    /*

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
