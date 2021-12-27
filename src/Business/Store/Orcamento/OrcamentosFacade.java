package Business.Store.Orcamento;

import java.util.Map;
import java.util.stream.Collectors;

public class OrcamentosFacade implements IGestOrcamento {

    private Map<String,Orcamento> orcamentos;

    public OrcamentosFacade(Map<String, Orcamento> orcamentos){
        this.orcamentos = orcamentos.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e-> e.getValue().clone()));
    }

    public Map<String,Orcamento> getOrcamentos() {
        return this.orcamentos.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e-> e.getValue().clone()));
    }
}
