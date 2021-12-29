package Business.Store.Servico;

import java.time.Duration;
import java.util.Map;

public interface IGestServicos {

    Map<Integer,Servico> getServicos();

    void adicionaServicoExpresso(float custo, Duration tempo);

    void consultaServico(Integer id);

    void removeServico(Integer id);

    boolean existeServico(int id);
}
