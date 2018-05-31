import java.util.ArrayList;
import java.util.List;

public class GooseGame implements IGooseGame {
    private static final String ADD_PLAYER = "add player ";
    private static final String MOVE = "move ";
    private static final int FINAL_SPACE = 63;
    private static final int BRIDGE_SPACE_START = 6;
    private static final int BRIDGE_SPACE_FINAL = 12;

    private static final String MOVE_RESULT_MESSAGE = "$PLAYER rolls $DICE_ROLLS. $PLAYER moves from $INITIAL_POS to $FINAL_POS";
    private static final String MOVE_BOUNCE_MESSAGE = ". $PLAYER bounces! $PLAYER returns to $BOUNCE_POS";
    private static final String MOVE_BRIDGE_MESSAGE = ". $PLAYER jumps to $BRIDGE_POS";


    public GooseGame(IDice dice) {
        this.dice = dice;
    }

    private IDice dice;

    private List<Player> players = new ArrayList<Player>();
    private Player currentPlayer = null;


    public String executeCommand(String command) {
        String result = "";

        if (isAddPlayerCommand(command)) {
            result = addPlayer(command);
        } else if (isMoveCommand(command)) {
            result = move(command);
        }

        return result;
    }

    private String move(String command) {
        String subCommand = command.replace(MOVE, "");

        String playerName = extractPlayerNameFromCommand(subCommand);

        currentPlayer = getPlayerByName(playerName);

        String diceRolls = extractDiceRolls(subCommand, playerName).trim();

        String[] moves = diceRolls.split(",");

        int initialPosition = currentPlayer.getPosition();

        for (String move : moves) {
            currentPlayer.setPosition(currentPlayer.getPosition() + Integer.parseInt(move.trim()));
        }

        String resultMove = MOVE_RESULT_MESSAGE;

        if (checkPlayerWins()) {
            resultMove += ". " + currentPlayer.getName() + " Wins!!";
        } else if (checkPlayerBounces()) {
            resultMove = playerBounce(resultMove);

            resultMove += MOVE_BOUNCE_MESSAGE;
        } else if (checkBridge()) {
            resultMove = playerBridge(resultMove);

            resultMove += MOVE_BRIDGE_MESSAGE;
        }

        resultMove = resultMove.replace("$PLAYER", currentPlayer.getName())
                .replace("$DICE_ROLLS", diceRolls)
                .replace("$INITIAL_POS", (initialPosition == 0 ? "Start" : initialPosition + ""))
                .replace("$FINAL_POS", (currentPlayer.getPosition() > FINAL_SPACE ? FINAL_SPACE + "" : currentPlayer.getPosition() + ""))
                .replace("$BOUNCE_POS", currentPlayer.getPosition() + "")
                .replace("$BRIDGE_POS", currentPlayer.getPosition() + "");

        return resultMove;

    }

    private String playerBridge(String resultMove) {
        resultMove = resultMove.replace("$FINAL_POS", (currentPlayer.getPosition() == BRIDGE_SPACE_START ? "The Bridge" : currentPlayer.getPosition() + ""));
        currentPlayer.setPosition(BRIDGE_SPACE_FINAL);
        return resultMove;
    }

    private boolean checkBridge() {
        return currentPlayer.getPosition() == BRIDGE_SPACE_START;
    }

    private String playerBounce(String resultMove) {
        resultMove = resultMove.replace("$FINAL_POS", (currentPlayer.getPosition() > FINAL_SPACE ? FINAL_SPACE + "" : currentPlayer.getPosition() + ""));
        currentPlayer.setPosition(FINAL_SPACE - (currentPlayer.getPosition() - FINAL_SPACE));
        return resultMove;
    }

    private boolean checkPlayerBounces() {
        return currentPlayer.getPosition() > FINAL_SPACE;
    }

    private boolean checkPlayerWins() {
        return currentPlayer.getPosition() == FINAL_SPACE;
    }

    private String extractDiceRolls(String subCommand, String playerName) {
        String diceRolls = subCommand.replace(playerName, "");

        if (diceRolls.equals("")) {
            diceRolls = dice.dicesRoll();
        }

        return diceRolls;
    }

    private String extractPlayerNameFromCommand(String subCommand) {
        for (Player player : players) {
            if (subCommand.indexOf(player.getName()) != -1) {
                return player.getName();
            }
        }

        return null;
    }

    private boolean isMoveCommand(String command) {
        return command.startsWith(MOVE);
    }

    private String addPlayer(String command) {
        String result;
        final String playerName = command.replace(ADD_PLAYER, "");

        if (getPlayerByName(playerName) != null) {
            result = playerName + ": already existing player";
        } else {
            players.add(new Player(playerName));
            result = "players: ";

            for (Player player : players) {
                result += player.getName() + ", ";
            }

            result = result.substring(0, result.length() - 2);
        }
        return result;
    }

    private Player getPlayerByName(String playerName) {
        for (Player player : players) {
            if (player.getName().equals(playerName)) {
                return player;
            }
        }
        return null;
    }

    private boolean isAddPlayerCommand(String command) {
        return command.startsWith(ADD_PLAYER);
    }


}
