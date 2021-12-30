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

            String res = "*** ID Técnico: "+idF+
                    " ***\n-- Serviço Expresso --\nTotal: "+nrServicosExpresso +
                    "\n--Serviço Programado--\nTotal: "+nrServicosProgramados +
                    "\nTempo total: "+somatorioDuracao+"\nTempo médio: "+meanDuracao;
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
            String res = "*** ID Recicionista: "+idF+
                    "\nEquipamentos recebidos: "+nrRecebidos +
                    "\nEquipamentos entregues: "+nrEntregues;
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
            sb.append("*** ID Técnico: ").append(idF).append("\n");
            for(Servico s : this.model.getServicosFacade().getServicos().values()){
                PlanoTrabalho pt = this.model.getPlanosFacade().getPlanos().get(s.getIdPlano());
                if(pt.getIdTecnico().equals(idF)) {
                    sb.append(pt);
                }
            }
            sb.append("\n\n");
        }
        return sb.toString();
    }
}
