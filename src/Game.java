import java.util.Scanner;

public class Game {

    private static char difficultyLvlChar;
    private static int N;
    private static boolean isGame = true;

    private static final Scanner IN = new Scanner(System.in);
    private static final String ASK_DIF_LVL_MSG = "Enter difficulty level:" +
            "\n'E' for easy\n'M' for medium\n'H' for hard\n>>> ";
    private static final String ASK_N_MSG = "Enter number of rows / columns: ";
    private static final String ASK_MOVE_TYPE_MSG = "Choose move type. 0 to uncover, 1 to flag: ";
    private static final String ASK_ROW = "Enter row: ";
    private static final String ASK_COLUMN = "Enter column: ";
    private static final String END_LOSS_MSG = "Game over. You lost.";
    private static final String END_WIN_MSG = "Game over. You won.";
    private static final String ERROR_MSG = "ERROR";



    public static void main(String[] args) {
        difficultyLvlChar = askDifficultyLvl();
        N = askN();
        Table table = new Table(getN(), getDifficultyLvlChar());
        System.out.println(table.getN());
        table.countAllMines();
        table.printer();

        Game.gameLoop(table);

    }


    public static char askDifficultyLvl() {
        char inputDiffLvl;
        do {
            System.out.print(ASK_DIF_LVL_MSG);
            inputDiffLvl = IN.next().charAt(0);
            inputDiffLvl = Character.toUpperCase(inputDiffLvl);
        } while (inputDiffLvl != 'E' && inputDiffLvl != 'M' && inputDiffLvl != 'H');

        return inputDiffLvl;
    }

    public static int askN() {
        int inputN;

        do{
            System.out.print(ASK_N_MSG);
            inputN = IN.nextInt();
        } while (inputN <= 0);

        return inputN;
    }

    public static int askMoveType() {
        //0 = uncover, 1 = flag
        int moveType;
        do {
            System.out.print(ASK_MOVE_TYPE_MSG);
            moveType = IN.nextInt();
        } while(moveType != 0 && moveType != 1);

        return moveType;
    }

    //askRow & askColumn both adjust the input row / col to 0-indexing
    public static int askRow() {
        int inputRow;

        do {
            System.out.print(ASK_ROW);
            inputRow = IN.nextInt();
        } while(inputRow > N || inputRow <= 0);

        return inputRow - 1;
    }

    //askRow & askColumn both adjust the input row / col to 0-indexing
    public static int askColumn() {
        int inputColumn;
        do{
            System.out.print(ASK_COLUMN);
            inputColumn = IN.nextInt();
        } while(inputColumn > N || inputColumn <= 0);

        return inputColumn - 1;
    }

    public static void makeMove(int moveTypeParam, int inputRowParam, int inputColumnParam, Table tableParam){
        switch (moveTypeParam) {
            case 0:
                Cell chosenUncoveredCell = tableParam.getCell(inputRowParam, inputColumnParam);
                chosenUncoveredCell.setIsClicked();
                tableParam.clickMinelessNeighbours(chosenUncoveredCell);
                break;
            case 1:
                Cell chosenFlaggedCell = tableParam.getCell(inputRowParam, inputColumnParam);
                chosenFlaggedCell.setIsFlagged();
                break;
            default:
                System.out.println(ERROR_MSG);
                break;
        }
    }

    //the isClickedCounter needs to account for unrevealed mined cells
    //i.e. isClickedCounter has to equal (N * N - numberOfMines)
    public static void checkMove(Cell chosenCellParam, Table tableParam) {
        Cell[][] table = tableParam.getTABLE();
        if(chosenCellParam.getIsMine() && !chosenCellParam.getIsFlagged()) {
            endGame(0);
        } else if (!chosenCellParam.getIsFlagged()){
            int isClickedCounter = 0;
            int numberOfMines = tableParam.getNumberOfMines();
            for(int i = 0; i < getN(); i++) {
                for(int j = 0; j < getN(); j++) {
                    if(table[i][j].getIsClicked())
                        isClickedCounter++;
                }
            }
            if(((getN() * getN()) - numberOfMines) == isClickedCounter) {
                endGame(1);
            }
        }

    }

    public static void endGame(int winParam) {
        if (winParam == 0) {
            System.out.println(END_LOSS_MSG);
            isGame = false;
        } else if (winParam == 1){
                System.out.println(END_WIN_MSG);
                isGame = false;
            }

        }

    public static void gameLoop(Table tableParam) {

        firstMove(tableParam);

        while(isGame) {
            int inputRow = askRow();
            int inputColumn = askColumn();
            int moveType = askMoveType();
            Cell chosenCell = tableParam.getCell(inputRow, inputColumn);
            makeMove(moveType, inputRow, inputColumn, tableParam);
            tableParam.printer();
            Game.checkMove(chosenCell, tableParam);
        }
    }

    public static void firstMove(Table tableParam){
        int inputRow = askRow();
        int inputColumn = askColumn();
        int moveType = askMoveType();
        Cell chosenCell = tableParam.getCell(inputRow, inputColumn);
        makeMove(moveType, inputRow, inputColumn, tableParam);
        tableParam.printer();
        Game.checkFirstMove(chosenCell, tableParam);
    }

    public static void checkFirstMove(Cell chosenCellParam, Table tableParam) {
        Cell[][] table = tableParam.getTABLE();
        if(chosenCellParam.getIsMine()) {
            chosenCellParam.setIsMine(false);
        } else if (!chosenCellParam.getIsFlagged()){
            int isClickedCounter = 0;
            int numberOfMines = tableParam.getNumberOfMines();
            for(int i = 0; i < getN(); i++) {
                for(int j = 0; j < getN(); j++) {
                    if(table[i][j].getIsClicked())
                        isClickedCounter++;
                }
            }
            if(((getN() * getN()) - numberOfMines) == isClickedCounter) {
                endGame(1);
            }
        }

    }

    public static int getN() {
        return N;
    }

    public static char getDifficultyLvlChar() {
        return difficultyLvlChar;
    }

}

