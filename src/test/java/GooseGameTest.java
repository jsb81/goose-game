import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GooseGameTest {

    private GooseGame gooseGame;

    @Before
    public void setUp(){
        gooseGame = new GooseGame(new DiceTest("1, 2"));
    }

    @Test
    public void add_one_player_test(){
        Assert.assertEquals("players: Pippo", gooseGame.executeCommand("add player Pippo"));
    }

    @Test
    public void add_another_player_test(){
        gooseGame.executeCommand("add player Pippo");
        Assert.assertEquals("players: Pippo, Pluto", gooseGame.executeCommand("add player Pluto"));
    }

    @Test
    public void add_duplicated_player_test(){
        gooseGame.executeCommand("add player Pippo");
        Assert.assertEquals("Pippo: already existing player", gooseGame.executeCommand("add player Pippo"));
    }


    @Test
    public void first_move(){
        gooseGame.executeCommand("add player Pippo");
        gooseGame.executeCommand("add player Pluto");

        Assert.assertEquals("Pippo rolls 4, 1. Pippo moves from Start to 5", gooseGame.executeCommand("move Pippo 4, 1"));
    }

    @Test
    public void first_move_second_player(){
        gooseGame.executeCommand("add player Pippo");
        gooseGame.executeCommand("add player Pluto");
        gooseGame.executeCommand("move Pippo 4, 2");

        Assert.assertEquals("Pluto rolls 2, 2. Pluto moves from Start to 4", gooseGame.executeCommand("move Pluto 2, 2"));
    }

    @Test
    public void second_move_second_player(){
        gooseGame.executeCommand("add player Pippo");
        gooseGame.executeCommand("add player Pluto");
        gooseGame.executeCommand("move Pippo 4, 1");
        gooseGame.executeCommand("move Pluto 2, 2");

        Assert.assertEquals("Pippo rolls 2, 3. Pippo moves from 5 to 10", gooseGame.executeCommand("move Pippo 2, 3"));
    }

    @Test
    public void first_player_arrives_in_63_position_and_wins(){
        gooseGame.executeCommand("add player Pippo");
        gooseGame.executeCommand("add player Pluto");
        gooseGame.executeCommand("move Pippo 60");


        Assert.assertEquals("Pippo rolls 1, 2. Pippo moves from 60 to 63. Pippo Wins!!", gooseGame.executeCommand("move Pippo 1, 2"));
    }
    @Test
    public void first_player_bounces(){
        gooseGame.executeCommand("add player Pippo");
        gooseGame.executeCommand("add player Pluto");
        gooseGame.executeCommand("move Pippo 60");


        Assert.assertEquals("Pippo rolls 3, 2. Pippo moves from 60 to 63. Pippo bounces! Pippo returns to 61", gooseGame.executeCommand("move Pippo 3, 2"));
    }


    @Test
    public void system_dice_roll(){
        gooseGame.executeCommand("add player Pippo");
        gooseGame.executeCommand("add player Pluto");
        gooseGame.executeCommand("move Pippo 4");


        Assert.assertEquals("Pippo rolls 1, 2. Pippo moves from 4 to 7", gooseGame.executeCommand("move Pippo"));
    }

    @Test
    public void bridge_space_test(){
        GooseGame gooseGame = new GooseGame(new DiceTest("1, 1"));

        gooseGame.executeCommand("add player Pippo");
        gooseGame.executeCommand("add player Pluto");
        gooseGame.executeCommand("move Pippo 4");


        Assert.assertEquals("Pippo rolls 1, 1. Pippo moves from 4 to The Bridge. Pippo jumps to 12", gooseGame.executeCommand("move Pippo"));
    }
}
