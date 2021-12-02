package Business.Cliente;

import java.io.Serializable;

public abstract class Cliente implements Serializable {
    private int id;
    private String name;
    private int nif;
    private int telemovel;
    private String mail;

    /**
     * Construtor Vázio
     */
    public Cliente(){
        this.id = 0;
        this.name = "n/a";
        this.nif = 0;
        this.telemovel = 0;
        this.mail = "n/a";
    }

    public Cliente(int id,String name, int nif, int telemovel,String mail){
        this.id = id;
        this.name = name;
        this.nif = nif;
        this.telemovel = telemovel;
        this.mail = mail;
    }

    public Cliente(Cliente c){
        this.id = c.getId();
        this.name = c.getName();
        this.nif = c.getNif();
        this.telemovel = c.getTelemovel();
        this.mail = c.getMail();
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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
}
