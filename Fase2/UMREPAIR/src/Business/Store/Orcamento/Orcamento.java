package Business.Store.Orcamento;

import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


public class Orcamento {
    private int idOrcamento;
    private String idEquip;              //equipamento
    private LocalDateTime data;
    private float custo;
    private Duration prazo;
    private String tecnico;
    private String notas;
    private int idPlano;
    private boolean status;

    public Orcamento(){
        this.idOrcamento = -1;
        this.idEquip = null;
        this.data = LocalDateTime.now();
        this.custo = -1;
        this.prazo = Duration.ZERO;
        this.tecnico = null;
        this.notas = null;
        this.idPlano = -1;
        this.status = false;
    }

    public Orcamento(int id, String equipamento, LocalDateTime data, float custo, Duration prazo, String tecnico, String notas, int plano, boolean status){
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
        this.prazo = Duration.ZERO;
        this.tecnico = null;
        this.notas = notas;
        this.idPlano = -1;
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

    public Duration getPrazo() {
        return prazo;
    }

    public void setPrazo(Duration prazo) {
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

    public int getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(int idPlano) {
        this.idPlano = idPlano;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void gravar(PrintWriter print){
        DateTimeFormatter dataformatada = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        print.println(this.idOrcamento + ";" + this.idEquip + ";" + this.data.format(dataformatada) + ";" + this.custo + ";" + this.prazo.toString() + ";" + this.tecnico + ";" + this.notas + ";" + this.idPlano + ";" + this.status);
    }

    public String toString(){
        DateTimeFormatter dataformatada = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String estado;
        if(status) estado = "concluído";
                else estado = "incompleto";
        return "\033[1;35mID: \033[0m" + idOrcamento  +
                " \033[1;35mEquipamento: \033[0m" + idEquip +
                " \033[1;35mData: \033[0m" + data.format(dataformatada) +
                " \033[1;35mCusto: \033[0m" + custo +
                " \033[1;35mPrazo: \033[0m" + prazo.toString() +
                " \033[1;35mTecnico: \033[0m" + tecnico +
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
        return idOrcamento == orcamento.idOrcamento && Float.compare(orcamento.custo, custo) == 0 && idPlano == orcamento.idPlano && status == orcamento.status && Objects.equals(idEquip, orcamento.idEquip) && Objects.equals(data, orcamento.data) && Objects.equals(prazo, orcamento.prazo) && Objects.equals(tecnico, orcamento.tecnico) && Objects.equals(notas, orcamento.notas);
    }
}
