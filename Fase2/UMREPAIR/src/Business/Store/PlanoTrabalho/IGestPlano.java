package Business.Store.PlanoTrabalho;

import java.time.Duration;
import java.util.Map;

public interface IGestPlano {

    Map<Integer, PlanoTrabalho> getPlanos();

    void atualizaPlano(int idPlano, String descricao, float custo, Duration prazo);

    void adicionaPlano(int idPlano, int idOrc, String idTecnico);

    int adicionaPlano(int idOrc, String idTecnico);

    boolean existePlano(int id);
}
