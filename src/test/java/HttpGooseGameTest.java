import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HttpGooseGameTest {
    private final HttpServer httpServer = new HttpServer();
    private Application application;

    @Before
    public void setUp() throws Exception {
        application = new Application();
        application.start();
    }

    @After
    public void stop() throws Exception{
        application.stop();
    }

    @Test
    public void prova_chiamata_http() {
        try {
            String url = "http://localhost:333/goose/players?name=Pippo";

            Assert.assertEquals("players: Pippo", httpServer.httpPost(url));

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void prova_chiamata_http_two_players() {
        try {
            httpServer.httpPost("http://localhost:333/goose/players?name=Pippo");

            Assert.assertEquals("players: Pippo, Pluto", httpServer.httpPost("http://localhost:333/goose/players?name=Pluto"));

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void prova_chiamata_http_move_player() {
        try {
            httpServer.httpPost("http://localhost:333/goose/players?name=Pippo");
            httpServer.httpPost("http://localhost:333/goose/players?name=Pluto");

            Assert.assertEquals("Pippo rolls 4, 5. Pippo moves from Start to 9", httpServer.httpPost("http://localhost:333/goose/move?name=Pippo&dice1=4&dice2=5"));

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
