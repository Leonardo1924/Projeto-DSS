package Business;

import java.io.IOException;

public interface IStoreLN {
    void start();

    boolean login(String user,String password) throws IOException;

    void shutdown();
}
