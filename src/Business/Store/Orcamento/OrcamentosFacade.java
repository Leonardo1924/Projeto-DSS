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

    public void removeOrcamento(Integer id) {
        this.orcamentos.remove(id);
    }


    public void registaOrcamento(int idOrc, String idEq, LocalDateTime now, String notas){
        Orcamento orc = new Orcamento(idOrc,idEq,now,notas);
        this.orcamentos.put(idOrc,orc);
    }

    public void atualizaOrcamento(Integer idOrc, float custo, int prazo, String idTecnico, String idPlano) {
        this.orcamentos.get(idOrc).setCusto(custo);
        this.orcamentos.get(idOrc).setPrazo(prazo);
        this.orcamentos.get(idOrc).setTecnico(idTecnico);
        this.orcamentos.get(idOrc).setIdPlano(idPlano);
        this.orcamentos.get(idOrc).setStatus(true);
    }

    public void consultaOrcamentos(){
        for(Orcamento o : this.orcamentos.values())
            System.out.println(o.toString());
    }
}
