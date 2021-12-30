package Business.Store.Servico;

import java.time.Duration;
import java.util.Map;

public interface IGestServicos {

    Map<Integer,Servico> getServicos();

    void adicionaServicoExpresso(float custo, Duration tempo);

    String consultaServico(Integer id);

    void removeServico(Integer id);

    boolean existeServico(int id);

    void adicionaServicoProgramado(int newID, int newPlano, String tipo);

    void atualizaValores(int id, float custoAtualizado, Duration d);
}
