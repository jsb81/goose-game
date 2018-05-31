import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class HttpHelloWorldServiceTest {
    private final HttpServer httpServer = new HttpServer();

    @Test
    public void prova_chiamata_http() {
        try {
            String url = "http://localhost:333/hello";

            String result = httpServer.httpGet(url);

            Assert.assertEquals("Viva il mondo", result);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
