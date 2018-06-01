
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;


public class Application {

    private static IGooseGame gooseGame;
    private static Server server;

    public static void main(String[] args) throws Exception{
        (new Application()).start();
    }

    public void start() throws Exception {
        server = new Server(9090);
        ServletContextHandler servletContextHandler = new ServletContextHandler();
        servletContextHandler.addServlet(new ServletHolder(new HttpHelloWorldSercice()), "/hello");

        gooseGame = new GooseGame(new Dice());
        HttpGooseGameService gooseGameService = new HttpGooseGameService(gooseGame);

        servletContextHandler.addServlet(new ServletHolder(gooseGameService), "/goose/players");
        servletContextHandler.addServlet(new ServletHolder(gooseGameService), "/goose/move");


        server.setHandler(servletContextHandler);
        server.start();
    }

    public  void stop() throws Exception {
        server.stop();
    }

}
