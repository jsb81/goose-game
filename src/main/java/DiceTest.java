public class DiceTest implements IDice {

    private String result;

    public DiceTest(String result) {
        this.result = result;
    }

    public String dicesRoll(){
        return this.result;
    }
}
