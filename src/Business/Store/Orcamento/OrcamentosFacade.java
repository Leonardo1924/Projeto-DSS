package Business.Store.Orcamento;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

public class OrcamentosFacade implements IGestOrcamento {

    private Map<Integer,Orcamento> orcamentos;

    public OrcamentosFacade(Map<Integer, Orcamento> orcamentos){
        this.orcamentos = orcamentos.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e-> e.getValue().clone()));
    }

    public Map<Integer,Orcamento> getOrcamentos() {
        return this.orcamentos.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e-> e.getValue().clone()));
    }

    public void registaOrcamento(int id,String idEq, LocalDateTime data, float custo, int prazo, String tecnico, String notas, String idPlano){
        Orcamento orc = new Orcamento(id, idEq, data,custo,prazo,tecnico,notas,idPlano);
        this.orcamentos.put(id,orc);
    }

    public void consultaOrcamentos(){
        for(Orcamento o : this.orcamentos.values())
            System.out.println(o.toString());
    }
}
