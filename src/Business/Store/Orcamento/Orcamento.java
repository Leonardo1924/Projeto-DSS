package Business.Store.Orcamento;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import Business.Store.PlanoTrabalho;
import Business.Store.Equipamento.Equipamento;
import Business.Store.Funcionario.Funcionario;


public class Orcamento {
    private int idOrcamento;
    private String idEquip;              //equipamento
    private LocalDateTime data;
    private float custo;
    private int prazo;  
    private String tecnico;
    private String notas;
    private String idPlano;

    public Orcamento(){
        this.idOrcamento = 0;
        this.idEquip = "";
        this.data = LocalDateTime.now();
        this.custo = 0;
        this.prazo = 0;
        this.tecnico = "";
        this.notas = "";
        this.idPlano = "";
    }

    public Orcamento(int id, String equipamento, LocalDateTime data, float custo, int prazo, String tecnico, String notas, String plano){
        this.idOrcamento = id;
        this.idEquip = equipamento;
        this.data = LocalDateTime.now();
        this.custo = custo;
        this.prazo = prazo;
        this.tecnico = tecnico;
        this.notas = notas;
        this.idPlano = plano;
    }

    public Orcamento(Orcamento orcamento){
        this.idOrcamento = orcamento.getIdOrcamento();
        this.idEquip = orcamento.getEquipamento();
        this.data = orcamento.getData();
        this.custo = orcamento.getCusto();
        this.prazo = orcamento.getPrazo();
        this.tecnico = orcamento.getTecnico();
        this.notas = orcamento.getNotas();
        this.idPlano = orcamento.getPlano();
    }

    public int getIdOrcamento() {
        return idOrcamento;
    }

    public void setIdOrcamento(int idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    public LocalDateTime getData(){
        return this.data;
    }

    public String getEquipamento(){
        return this.idEquip;
    }

    public float getCusto(){
        return this.custo;
    }

    public int getPrazo(){
        return this.prazo;
    }

    public String getTecnico(){
        return this.tecnico;
    }

    public String getNotas(){
        return this.notas;
    }

    public String getPlano(){
        return this.idPlano;
    }

    public void setData(LocalDateTime data){
        this.data = data;
    }

    public void setEquipamento(String equipamento){
        this.idEquip = equipamento;
    }

    public void setCusto(float custo){
        this.custo = custo;
    }

    public void setFuncionario(String tecnico){
        this.tecnico = tecnico;
    }

    public void setNotas(String notas){
        this.notas = notas;
    }

    public void setPlano(String plano){
        this.idPlano = plano;
    }

    /*public void calculaPrazo(){
        this.prazo = this.plano.tempoTotalPlano();
    }*/
/*
    public void calcularCusto(){  
        String somaTempo = this.plano.tempoTotalPlano().toString();
        float somaCusto = this.plano.custoTotalPlano();
        this.custo = Float.parseFloat(somaTempo)*0.25 + somaCusto;        // 25€ por hora
    }
*/
    public String toString(){
        DateTimeFormatter dataformatada = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return "\033[1;35mID: \033[0m" + idOrcamento  +
                " \033[1;35mEquipamento: \033[0m" + idEquip +
                " \033[1;35mData: \033[0m" + data.format(dataformatada) +
                " \033[1;35mCusto: \033[0m" + custo +
                " \033[1;35mPrazo: \033[0m" + prazo +
                " \033[1;35mFuncionario: \033[0m" + tecnico +
                " \033[1;35mNotas: \033[0m" + notas +
                " \033[1;35mPlano: \033[0m" + idPlano;
    }

    public Orcamento clone(){
        return new Orcamento(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orcamento orcamento = (Orcamento) o;
        return idOrcamento == orcamento.idOrcamento && Float.compare(orcamento.custo, custo) == 0 && prazo == orcamento.prazo && Objects.equals(idEquip, orcamento.idEquip) && Objects.equals(data, orcamento.data) && Objects.equals(tecnico, orcamento.tecnico) && Objects.equals(notas, orcamento.notas) && Objects.equals(idPlano, orcamento.idPlano);
    }
}
