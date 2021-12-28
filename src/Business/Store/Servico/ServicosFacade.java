package Business.Store.Servico;

import java.util.Map;
import java.util.stream.Collectors;

public class ServicosFacade implements IGestServicos {

    private Map<Integer, Servico> servicos;

    public ServicosFacade(Map<Integer, Servico> servicos){
        this.servicos = servicos.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e-> e.getValue().clone()));
    }

    public Map<Integer,Servico> getServicos() {
        return this.servicos.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e-> e.getValue().clone()));
    }
}
