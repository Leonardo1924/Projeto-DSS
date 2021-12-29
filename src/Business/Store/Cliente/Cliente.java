package Business.Store.Cliente;

import java.io.PrintWriter;
import java.io.Serializable;

public class Cliente implements Serializable {
    private String idCliente;
    private String name;
    private int nif;
    private int telemovel;
    private String mail;
    private String idEquip;

    public Cliente(){
        this.idCliente = "n/a";
        this.name = "n/a";
        this.nif = 0;
        this.telemovel = 0;
        this.mail = "n/a";
        this.idEquip = "n/a";
    }

    public Cliente(String id,String name, int nif, int telemovel, String mail, String idEquip){
        this.idCliente = id;
        this.name = name;
        this.nif = nif;
        this.telemovel = telemovel;
        this.mail = mail;
        this.idEquip = idEquip;
    }

    public Cliente(Cliente c){
        this.idCliente = c.getIdCliente();
        this.name = c.getName();
        this.nif = c.getNif();
        this.telemovel = c.getTelemovel();
        this.mail = c.getMail();
        this.idEquip = c.getEquipamento();
    }

    public String getIdCliente() {
        return this.idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNif() {
        return this.nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public int getTelemovel() {
        return this.telemovel;
    }

    public void setTelemovel(int telemovel) {
        this.telemovel = telemovel;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getEquipamento(){
        return this.idEquip;
    }

    public void setEquipamento(String idEquip){
        this.idEquip = idEquip;
    }

    public Cliente clone(){
        return new Cliente(this);
    }

    public void gravar(PrintWriter print){
        print.println(this.idCliente + ";" + this.name + ";" + this.nif + ";" + this.telemovel + ";" + this.mail + ";" + this.idEquip);
    }

    public String toString(){
        return  "\033[1;35mId: \033[0m" + idCliente +
                " \033[1;35mNome: \033[0m" + name +
                " \033[1;35mNIF: \033[0m" + nif +
                " \033[1;35mTelemovel: \033[0m" + telemovel +
                " \033[1;35mMail: \033[0m" + mail +
                " \033[1;35mEquipamento: \033[0m" + idEquip;
    }

    public boolean equals(Object obj){
        if (obj == this) return true;
        if (obj == null || obj.getClass().equals(this.getClass())) return false;
        Cliente client = (Cliente) obj;
        return this.idCliente == client.getIdCliente() &&
                this.name.equals(client.getName()) &&
                this.nif == client.getNif() &&
                this.telemovel == client.getTelemovel() &&
                this.mail.equals(client.getMail()) &&
                this.idEquip.equals(client.getEquipamento());
    }
}