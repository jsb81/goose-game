public class Dice implements IDice {


    public Dice() {
    }

    public String dicesRoll() {
        int dice1 = diceRoll();
        int dice2 = diceRoll();

        return dice1 + ", " + dice2;
    }

    private int diceRoll() {
        return (int) (Math.random() * (5.0)) + 1;
    }
}