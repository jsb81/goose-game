public class GooseGameDummy implements IGooseGame {
    public String executeCommand(String command) {

        if(command.equals("add player Pippo"))
            return "players: Pippo";

        return "players: Pippo, Pluto";
    }
}
