package Business.Store.Orcamento;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

public class OrcamentosFacade implements IGestOrcamentos {

    private Map<Integer,Orcamento> orcamentos;

    public OrcamentosFacade(Map<Integer, Orcamento> orcamentos){
        this.orcamentos = orcamentos.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e-> e.getValue().clone()));
    }

    public Map<Integer,Orcamento> getOrcamentos() {
        return this.orcamentos.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e-> e.getValue().clone()));
    }

    public boolean removeOrcamento(Integer id) {
        if(this.orcamentos.containsKey(id)) {
            this.orcamentos.remove(id);
            return true;
        }
        return false;
    }


    public void registaOrcamento(int idOrc,String idEq,LocalDateTime now,String notas){
        Orcamento orc = new Orcamento(idOrc,idEq,now,notas);
        this.orcamentos.put(idOrc,orc);
    }

    public void atualizaOrcamento(int idOrc, String idTecnico, int idPlano, float custoTotal, Duration prazoTotal) {
        Orcamento o = this.orcamentos.get(idOrc);
        o.setTecnico(idTecnico);
        o.setIdPlano(idPlano);
        o.setCusto(custoTotal);
        o.setPrazo(prazoTotal);
        o.setStatus(true);
        this.orcamentos.remove(idOrc);
        this.orcamentos.put(idOrc,o);

    }

    public void atualizaOrcamento(int idOrc, float custoTotal, Duration prazoTotal) {

    }


    public void consultaOrcamentos(){
        for(Orcamento o : this.orcamentos.values())
            System.out.println(o.toString());
    }

    public boolean existeOrcamento(int id){
        return this.orcamentos.containsKey(id);
    }
}
