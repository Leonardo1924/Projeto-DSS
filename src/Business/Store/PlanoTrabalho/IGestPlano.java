package Business.Store.PlanoTrabalho;

import java.time.Duration;
import java.util.Map;

public interface IGestPlano {

    Map<Integer, PlanoTrabalho> getPlanos();

    int adicionaPlano(int idOrc, String idTecnico, float custo, Duration prazo);
}
