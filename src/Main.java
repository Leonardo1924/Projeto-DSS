import UI.TextUI;

/**
 * @author Grupo 24
 */
public class Main {
    /**
     * O método main cria a aplicação e invoca o método run()
     * @param args
     */
    public static void main(String[] args){
        try{
            new TextUI().run();
        }
        catch (Exception e) {
            System.out.println("Não foi possível iniciar o sistema!: " + e.getMessage());
        }
        System.out.println("\nO sistema será encerrado agora.");
        System.out.println(Integer.parseInt("aksjhd"));
        System.out.println("\033[1;36m" + "Sessão Terminada!" + "\033[0m");
    }
}
