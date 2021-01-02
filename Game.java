import java.util.Scanner;

public class Game {

    private static char difficultyLvlChar;
    private static int N;
    private static boolean isGame = true;

    private static final Scanner IN = new Scanner(System.in);
    private static final String ASK_DIF_LVL_MSG = "Enter difficulty level:" +
            "\n'E' for easy\n'M' for medium\n'H' for hard\n>>> ";
    private static final String ASK_N_MSG = "Enter number of rows / columns: ";
    private static final String ASK_ROW = "Enter row: ";
    private static final String ASK_COLUMN = "Enter column: ";
    private static final String END_MSG = "Game over";

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
        } while (inputDiffLvl != 'E' && inputDiffLvl != 'M' && inputDiffLvl != 'H');

        return inputDiffLvl;
    }

    public static int askN() {
        int inputN;
        System.out.print(ASK_N_MSG);
        while (!IN.hasNextInt()) {
            System.out.print(ASK_N_MSG);
            IN.next();
        }
        inputN = IN.nextInt();

        return inputN;
    }

    public static int askRow() {
        int inputRow;

        do {
            System.out.print(ASK_ROW);
            inputRow = IN.nextInt();
        } while(inputRow > N && inputRow <= 0);

        return inputRow - 1;
    }

    public static int askColumn() {
        int inputColumn;
        do{
            System.out.print(ASK_COLUMN);
            inputColumn = IN.nextInt();
        } while(inputColumn > N && inputColumn <= 0);

        return inputColumn - 1;
    }

    public static void checkMove(Cell chosenCellParam) {
        if(chosenCellParam.getIsMine()) {
            endGame();
        }
    }

    public static void endGame() {
        System.out.println(END_MSG);
        isGame = false;
    }

    public static void gameLoop(Table tableParam) {
        while(isGame) {
            int inputRow = askRow();
            int inputColumn = askColumn();
            Cell chosenCell = tableParam.findCell(inputRow, inputColumn);
            chosenCell.setIsClicked();
            tableParam.clickMinelessNeighbours(chosenCell, tableParam.getTABLE());
            tableParam.printer();
            Game.checkMove(chosenCell);
        }
    }

    public static int getN() {
        return N;
    }

    public static char getDifficultyLvlChar() {
        return difficultyLvlChar;
    }

}
