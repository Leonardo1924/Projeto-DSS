package Business.Store;

import Business.Store.Funcionario.Funcionario;
import Business.Store.PlanoTrabalho.PlanoTrabalho;
import Business.Store.Servico.Servico;

import java.time.Duration;
import java.util.*;

public class Estatisticas {
    private IStoreLN model;

    public Estatisticas(IStoreLN model){
        this.model = model;
    }

    public String reportTecnico(){
        Set<String> idsTecnicos = new TreeSet<>();
        for(Funcionario f : this.model.getFuncionariosFacade().getFuncionarios().values()){
            String idF = f.getTipo();
            if(idF.equals("Tecnico"))
                idsTecnicos.add(f.getUsername());
        }

        StringBuilder sb = new StringBuilder();

        for(String idF : idsTecnicos){
            int nrServicosProgramados = 0;
            int nrServicosExpresso = 0;
            long tempoSEC = 0;
            for(Servico s : this.model.getServicosFacade().getServicos().values()){
                PlanoTrabalho pt = this.model.getPlanosFacade().getPlanos().get(s.getIdPlano());
                if(pt.getIdTecnico().equals(idF)) {
                    if (s.getTipo().equals("expresso"))
                        nrServicosExpresso++;
                    if (s.getTipo().equals("programado")) {
                        nrServicosProgramados++;
                        tempoSEC += pt.getPrazo().toSeconds();
                    }
                }
            }
            long mean = 0;
            if(nrServicosProgramados != 0)
                mean = tempoSEC / nrServicosProgramados;

            Duration somatorioDuracao = Duration.ofSeconds(tempoSEC);
            Duration meanDuracao = Duration.ofSeconds(mean);

            String res = "\033[1;36m*** ID Técnico: \033[0m"+idF+
                    "\033[0m\n\033[1;33m-- Serviço Expresso --\033[0m\n\033[1;35mTotal: \033[0m"+nrServicosExpresso +
                    "\033[1;33m\n--Serviço Programado--\033[0m\n\033[1;35mTotal: \033[0m"+nrServicosProgramados +
                    "\n\033[1;35mTempo total: \033[0m"+somatorioDuracao+"\033[1;36m\n\033[1;35mTempo médio: \033[0m"+meanDuracao;
            sb.append(res).append("\n\n");
        }
        return sb.toString();
    }

    public String reportRececionista() {
        Set<String> idsRececionistas = new TreeSet<>();
        for(Funcionario f : this.model.getFuncionariosFacade().getFuncionarios().values()){
            String idF = f.getTipo();
            if(idF.equals("Rececionista"))
                idsRececionistas.add(f.getUsername());
        }

        StringBuilder sb = new StringBuilder();

        for(String idF : idsRececionistas){
            int nrRecebidos = this.model.getFuncionariosFacade().getRececao().get(idF).size();;
            int nrEntregues = this.model.getFuncionariosFacade().getDevolucao().get(idF).size();;
            String res = "\033[1;36m*** ID Recicionista: \033[0m"+idF+
                    "\033[1;35m\nEquipamentos recebidos: \033[0m"+nrRecebidos +
                    "\033[1;35m\nEquipamentos entregues: \033[0m"+nrEntregues;
            sb.append(res).append("\n\n");
        }
        return sb.toString();
    }

    public String reportDetalhado() {
        Set<String> idsTecnicos = new TreeSet<>();
        for(Funcionario f : this.model.getFuncionariosFacade().getFuncionarios().values()){
            String idF = f.getTipo();
            if(idF.equals("Tecnico"))
                idsTecnicos.add(f.getUsername());
        }
        StringBuilder sb = new StringBuilder();
        for(String idF : idsTecnicos){
            sb.append("\033[1;36m*** ID Técnico: \033[0m").append(idF).append("\n");
            for(Servico s : this.model.getServicosFacade().getServicos().values()){
                PlanoTrabalho pt = this.model.getPlanosFacade().getPlanos().get(s.getIdPlano());
                if(pt.getIdTecnico().equals(idF)) {
                    sb.append("\033[1;33mServiço:\n\033[0m");
                    sb.append(s);
                    sb.append("\n");
                    sb.append("\033[1;33mPlano de Trabalho:\n\033[0m");
                    sb.append(pt);
                }
            }
            sb.append("\n\n");
        }
        return sb.toString();
    }
}
