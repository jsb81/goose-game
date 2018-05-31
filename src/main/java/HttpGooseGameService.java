import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class HttpGooseGameService extends HttpServlet {
    public HttpGooseGameService(IGooseGame gooseGame) {
        this.gooseGame = gooseGame;
    }

    private IGooseGame gooseGame;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println();
        if (req.getRequestURI().replace("/goose/","").equals("players")) {
            Map<String, String[]> parameters = req.getParameterMap();

            String playerName = parameters.get("name")[0];

            resp.getWriter().write(gooseGame.executeCommand("add player " + playerName));
        }else{
            Map<String, String[]> parameters = req.getParameterMap();

            String playerName = parameters.get("name")[0];
            int dice1 = Integer.parseInt(parameters.get("dice1")[0]);
            int dice2 = Integer.parseInt(parameters.get("dice2")[0]);

            resp.getWriter().write(gooseGame.executeCommand("move " + playerName + " " + dice1 + ", " + dice2));
        }
    }


}
