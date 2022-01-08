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

    public void atualizaPlano(int idPlano, String descricao, float custo, Duration prazo) {
        PlanoTrabalho pt = this.getPlanos().get(idPlano);
        pt.addPasso(descricao,custo,prazo);
        this.planos.remove(idPlano);
        this.planos.put(idPlano,pt);
    }

    public void adicionaPlano(int idPlano, int idOrc, String idTecnico) {
        PlanoTrabalho pt = new PlanoTrabalho(idPlano,idOrc,idTecnico);
        this.planos.put(idPlano,pt);
    }

    public int adicionaPlano(int idOrc, String idTecnico){
        int newID = this.getPlanos().size() +1;
        PlanoTrabalho pt = new PlanoTrabalho(newID,idOrc, idTecnico);
        this.planos.put(newID,pt);
        return newID;
    }

    public boolean existePlano(int id){
        return this.planos.containsKey(id);
    }

}
