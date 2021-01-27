import java.util.ArrayList;
import java.util.Random;

public class Cell {

    private final int row;
    private final int column;

    private final boolean isMine;
    private final int mineFactor;
    private final Random randomInt = new Random();
    private final int mineRandomVar = randomInt.nextInt(100);
    private final ArrayList<Cell> legalNeighbours = new ArrayList<Cell>();

    private boolean isClicked = false;
    private boolean isFlagged = false;
    private int minedNeighbours;




    public Cell(int diffLevelParam, int columnParam, int rowParam) {
        this.mineFactor = setMineFactor(diffLevelParam);
        this.isMine = isCellMine(getMineFactor(this.mineFactor), mineRandomVar);
        this.row = rowParam;
        this.column = columnParam;
    }

    public String toString() {

        String stringToReturn = "";

        if(!isClicked && !isFlagged) {
            stringToReturn = "[ ]";
        } else if (isFlagged && !isClicked) {
            stringToReturn = "[F]";
        } else if (isClicked && isMine) {
            stringToReturn = "[X]";
        }  else if(isClicked && getMinedNeighbours() == 0) {
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

    //sets the probability of a cell containing a mine
    private int setMineFactor(int difficultyLvlParam) {
        switch(difficultyLvlParam) {
            case 1:
                return 10;
            case 2:
                return 20;
            case 3:
                return 30;
            default:
                return -1;
        }
    }

    public void setIsClicked() {
        this.isClicked = true;
    }

    public void setIsFlagged() {
        this.isFlagged = true;
    }

    public void setLegalNeighbours(){
        int row = this.getRow();
        int column = this.getColumn();

        if(row == 0){
            if(column == 0){
                // left upper corner
            } else if(column == Game.getN() - 1){
                //right upper corner
            } else {
                // remainder of upper boundary
            }
            // right upper corner
        } else if(row == Game.getN() - 1){
            if(column == 0){
                //left lower corner
            } else if(column == Game.getN() - 1){
                //right lower corner
            } else {
                // remainder of lower boundary
            }
            // left lower corner
        } else if(row <= Game.getN() - 2 && row >= 2 && column == 0){
            // remainder of left boundary
        } else if(row <= Game.getN() - 2 && row >= 2 && column == Game.getN() - 1){
            // remainder of right boundary
        } else {
            // all 8 neighbours
        }
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

    public boolean getIsFlagged() {
        return this.isFlagged;
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
