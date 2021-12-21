public class PlanoTrabalho {
    private List<Passo> passos;

    public PlanoTrabalho(){
        this.passos = new ArrayList<Passo>();
    }

    public PlanoTrabalho(List<Passo> passos){
        this.passos.putAll(passos);
    }

    public PlanoTrabalho(PlanoTrabalho plano){
        this.passos = getPassos();
    }

    public List<Passo> getPassos(){
        return this.passos;
    }

    public void setPassos(List<Passo> passo){
        this.passos = new ArrayList<>();
        for(Passo p : passo) {
            this.passos.add(p);
        }
    }

    public PlanoTrabalho clone(){
        return new PlanoTrabalho(this);
    } 
}
