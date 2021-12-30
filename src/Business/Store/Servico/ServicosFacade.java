package Business.Store.Servico;

import java.time.Duration;
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

    @Override
    public void adicionaServicoExpresso(float custo, Duration tempo) {
        int id = this.servicos.size()+1;
        Servico se = new Servico(id,custo,tempo,"expresso");
        this.servicos.put(id,se);
    }

    @Override
    public String consultaServico(Integer id) {
            return this.servicos.get(id).toString();
    }

    @Override
    public void removeServico(Integer id) {
        this.servicos.remove(id);
    }

    public boolean existeServico(int id){
        return this.servicos.containsKey(id);
    }

    public void adicionaServicoProgramado(int newID, int newPlano, String tipo) {
        Servico se = new Servico(newID,newPlano, tipo);
        this.servicos.put(newID,se);
    }

    @Override
    public void atualizaValores(int id, float custoAtualizado, Duration d) {
        this.servicos.get(id).setCustoTotal(custoAtualizado);
        this.servicos.get(id).setTempoTotal(d);
    }
}
