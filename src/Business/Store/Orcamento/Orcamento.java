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
    private boolean status;

    public Orcamento(){
        this.idOrcamento = 0;
        this.idEquip = null;
        this.data = LocalDateTime.now();
        this.custo = 0;
        this.prazo = 0;
        this.tecnico = null;
        this.notas = null;
        this.idPlano = null;
        this.status = false;
    }

    public Orcamento(int id, String equipamento, LocalDateTime data, float custo, int prazo, String tecnico, String notas, String plano, boolean status){
        this.idOrcamento = id;
        this.idEquip = equipamento;
        this.data = data;
        this.custo = custo;
        this.prazo = prazo;
        this.tecnico = tecnico;
        this.notas = notas;
        this.idPlano = plano;
        this.status = status;
    }

    public Orcamento(Orcamento orcamento){
        this.idOrcamento = orcamento.getIdOrcamento();
        this.idEquip = orcamento.getIdEquip();
        this.data = orcamento.getData();
        this.custo = orcamento.getCusto();
        this.prazo = orcamento.getPrazo();
        this.tecnico = orcamento.getTecnico();
        this.notas = orcamento.getNotas();
        this.idPlano = orcamento.getIdPlano();
        this.status = orcamento.isStatus();
    }

    public Orcamento(int idOrc, String idEq, LocalDateTime now, String notas) {
        this.idOrcamento = idOrc;
        this.idEquip = idEq;
        this.data = now;
        this.custo = 0;
        this.prazo = 0;
        this.tecnico = null;
        this.notas = notas;
        this.idPlano = null;
        this.status = false;
    }


    public int getIdOrcamento() {
        return idOrcamento;
    }

    public void setIdOrcamento(int idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    public String getIdEquip() {
        return idEquip;
    }

    public void setIdEquip(String idEquip) {
        this.idEquip = idEquip;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public float getCusto() {
        return custo;
    }

    public void setCusto(float custo) {
        this.custo = custo;
    }

    public int getPrazo() {
        return prazo;
    }

    public void setPrazo(int prazo) {
        this.prazo = prazo;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(String idPlano) {
        this.idPlano = idPlano;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
        String estado;
        if(status) estado = "concluído";
                else estado = "incompleto";
        return "\033[1;35mID: \033[0m" + idOrcamento  +
                " \033[1;35mEquipamento: \033[0m" + idEquip +
                " \033[1;35mData: \033[0m" + data.format(dataformatada) +
                " \033[1;35mCusto: \033[0m" + custo +
                " \033[1;35mPrazo: \033[0m" + prazo +
                " \033[1;35mFuncionario: \033[0m" + tecnico +
                " \033[1;35mNotas: \033[0m" + notas +
                " \033[1;35mPlano: \033[0m" + idPlano +
                " \033[1;35mEstado: \033[0m" + estado ;
    }

    public Orcamento clone(){
        return new Orcamento(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orcamento orcamento = (Orcamento) o;
        return idOrcamento == orcamento.idOrcamento && Float.compare(orcamento.custo, custo) == 0 && prazo == orcamento.prazo && status == orcamento.status && Objects.equals(idEquip, orcamento.idEquip) && Objects.equals(data, orcamento.data) && Objects.equals(tecnico, orcamento.tecnico) && Objects.equals(notas, orcamento.notas) && Objects.equals(idPlano, orcamento.idPlano);
    }
}
