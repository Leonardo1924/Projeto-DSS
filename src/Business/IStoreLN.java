package Business;

public interface IStoreLN {
    void start();

    boolean login(String user,String password);

    void shutdown();
}
