import java.util.Random;

public class Cell {

    private boolean isMine;
    private int mineFactor;
    private boolean isClicked = false;
    private final Random randomInt = new Random();
    private final int mineRandomVar = randomInt.nextInt(100);
    private int minedNeighbours;

    private int row;
    private int column;


    public Cell(int diffLevelParam, int columnParam, int rowParam) {
        this.mineFactor = setMineFactor(diffLevelParam);
        this.isMine = isCellMine(getMineFactor(this.mineFactor), mineRandomVar);
        this.row = rowParam;
        this.column = columnParam;
    }

    public String toString() {

        String stringToReturn = "";

        if(!isClicked) {
            stringToReturn = "[ ]";
        } else if(isClicked && getMinedNeighbours() == 0) {
            stringToReturn = "[0]";
        } else if(isClicked && getMinedNeighbours() != 0) {
            stringToReturn = "[" + this.getMinedNeighbours() + "]";
        }

        return stringToReturn;
    }

    private boolean isCellMine(int mineFactorParam, int mineRandomVarParam) {
        if(mineFactorParam >= mineRandomVarParam) {
            return true;
        } else {
            return false;
        }
    }

    private int setMineFactor(int difficultyLvlParam) {
        switch(difficultyLvlParam) {
            case 1:
                return 5;
            case 2:
                return 10;
            case 3:
                return 15;
            default:
                return -1;
        }
    }

    public void setIsClicked() {
        this.isClicked = true;
    }

    public void setMinedNeighbours(int minedNeighboursParam) {
        this.minedNeighbours = minedNeighboursParam;
    }

    public int getMineFactor(int mineFactorParam) {
        return this.mineFactor;
    }

    public boolean getIsMine() {
        return this.isMine;
    }

    public boolean getIsClicked() {
        return this.isClicked;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public int getMinedNeighbours() {
        return this.minedNeighbours;
    }






}
