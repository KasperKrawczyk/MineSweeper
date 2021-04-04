import java.util.HashSet;

public class Table {

    private final int N;
    private final int difficultyLvl;
    private final int numberOfMines;

    private final Cell[][] TABLE;

    public Table(int nParam, char difficultyLvlCharParam) {
        this.N = setN(nParam);
        this.difficultyLvl = setDifficultyLvl(difficultyLvlCharParam);
        this.TABLE = setTable(nParam);
        this.numberOfMines = setNumberOfMines();
    }

    public Cell[][] setTable(int inputNParam) {

        Cell[][] Table = new Cell[inputNParam][inputNParam];

        for(int i = 0; i < inputNParam; i++){
            for(int j = 0; j < inputNParam; j++) {
                Table[i][j] = new Cell(getDifficultyLvl(), i, j);
            }
        }

        return Table;

    }

    //counts all mined cells in TABLE
    public void countAllMines() {
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++) {
                this.TABLE[i][j].setMinedNeighbours(countMines(i, j));
            }
        }
    }

    //counts mined neighbours of a given cell
    public int countMines(int inputRowParam, int inputColumnParam) {

        int i = inputRowParam;
        int j = inputColumnParam;
        int minedNeighbours = 0;

        if (i - 1 >= 0 && j - 1 >= 0 && this.TABLE[i - 1][j - 1].getIsMine()) {
            minedNeighbours++;
        }
        if (i - 1 >= 0 && this.TABLE[i - 1][j].getIsMine()) {
            minedNeighbours++;
        }
        if (i - 1 >= 0 && j + 1 < this.TABLE[0].length && this.TABLE[i - 1][j + 1].getIsMine()) {
            minedNeighbours++;
        }
        if (j - 1 >= 0 && this.TABLE[i][j - 1].getIsMine()) {
            minedNeighbours++;
        }
        if (j + 1 < this.TABLE.length && this.TABLE[i][j + 1].getIsMine()) {
            minedNeighbours++;
        }
        if (i + 1 < this.TABLE.length && j - 1 >= 0 && this.TABLE[i + 1][j - 1].getIsMine()) {
            minedNeighbours++;
        }
        if (i + 1 < this.TABLE.length && this.TABLE[i + 1][j].getIsMine()) {
            minedNeighbours++;
        }
        if (i + 1 < this.TABLE.length && j + 1 < this.TABLE[0].length && this.TABLE[i + 1][j + 1].getIsMine()) {
            minedNeighbours++;
        }

        return minedNeighbours;
    }

    public Cell getCell(int inputRowParam, int inputColumnParam) {
        return this.TABLE[inputRowParam][inputColumnParam];
    }

    //flood-fill
    public void clickMinelessNeighbours(Cell chosenCellParam){
        HashSet<Cell> minelessNeighbours = new HashSet<Cell>();

        minelessNeighbours.add(chosenCellParam);

        while(!minelessNeighbours.isEmpty()){
            Cell processedCell = minelessNeighbours.stream().findFirst().orElse(null);
            processedCell.setIsClicked();
            if(processedCell.getMinedNeighbours() != 0){
                break;
            }

            int i = processedCell.getColumn();
            int j = processedCell.getRow();


            if (i - 1 >= 0
                    && !this.TABLE[i - 1][j].getIsMine()
                    && !this.TABLE[i - 1][j].getIsClicked()) {
                if(this.TABLE[i - 1][j].getMinedNeighbours() == 0){
                    minelessNeighbours.add(this.TABLE[i - 1][j]);
                }
                clickMinelessNeighbours(this.TABLE[i - 1][j]);
            }

            if (i - 1 >= 0 && j - 1 >= 0
                    && !this.TABLE[i - 1][j - 1].getIsMine()
                    && !this.TABLE[i - 1][j - 1].getIsClicked()) {
                if(this.TABLE[i - 1][j - 1].getMinedNeighbours() == 0){
                    minelessNeighbours.add(this.TABLE[i - 1][j - 1]);
                }
                clickMinelessNeighbours(this.TABLE[i - 1][j - 1]);
            }

            if (j - 1 >= 0
                    && !this.TABLE[i][j - 1].getIsMine()
                    && !this.TABLE[i][j - 1].getIsClicked()) {
                if(this.TABLE[i][j - 1].getMinedNeighbours() == 0){
                    minelessNeighbours.add(this.TABLE[i][j - 1]);
                }
                clickMinelessNeighbours(this.TABLE[i][j - 1]);
            }

            if (i + 1 < this.TABLE.length && j - 1 >= 0
                    && !this.TABLE[i + 1][j - 1].getIsMine()
                    && !this.TABLE[i + 1][j - 1].getIsClicked()) {
                if(this.TABLE[i + 1][j - 1].getMinedNeighbours() == 0){
                    minelessNeighbours.add(this.TABLE[i + 1][j - 1]);
                }
                clickMinelessNeighbours(this.TABLE[i + 1][j - 1]);
            }

            if (i + 1 < this.TABLE.length
                    && !this.TABLE[i + 1][j].getIsMine()
                    && !this.TABLE[i + 1][j].getIsClicked()) {
                if(this.TABLE[i + 1][j].getMinedNeighbours() == 0){
                    minelessNeighbours.add(this.TABLE[i + 1][j]);
                }
                clickMinelessNeighbours(this.TABLE[i + 1][j]);
            }

            if (i + 1 < this.TABLE.length && j + 1 < this.TABLE.length
                    && !this.TABLE[i + 1][j + 1].getIsMine()
                    && !this.TABLE[i + 1][j + 1].getIsClicked()) {
                if(this.TABLE[i + 1][j + 1].getMinedNeighbours() == 0){
                    minelessNeighbours.add(this.TABLE[i + 1][j + 1]);
                }
                clickMinelessNeighbours(this.TABLE[i + 1][j + 1]);
            }

            if (j + 1 < this.TABLE.length
                    && !this.TABLE[i][j + 1].getIsMine()
                    && !this.TABLE[i][j + 1].getIsClicked()) {
                if(this.TABLE[i][j + 1].getMinedNeighbours() == 0){
                    minelessNeighbours.add(this.TABLE[i][j + 1]);
                }
                clickMinelessNeighbours(this.TABLE[i][j + 1]);
            }

            if (i - 1 >= 0 && j + 1 < this.TABLE.length
                    && !this.TABLE[i - 1][j + 1].getIsMine()
                    && !this.TABLE[i - 1][j + 1].getIsClicked()) {
                if(this.TABLE[i - 1][j + 1].getMinedNeighbours() == 0){
                    minelessNeighbours.add(this.TABLE[i - 1][j + 1]);
                }
                clickMinelessNeighbours(this.TABLE[i - 1][j + 1]);
            }

            minelessNeighbours.remove(processedCell);



        }
    }

    public void printer() {
        String header = "      ";
        for(int column = 1; column <= Game.getN(); column++) {
            //prints the numbers of the columns
            header += column + "  ";
        }

        System.out.printf("%-10s", header + "\n");

        for(int i = 0; i < Game.getN(); i++) {
            //prints the number of the row
            System.out.printf("%-5s", i + 1);
            String rowToPrint = "";
            for(int j = 0; j < Game.getN(); j++) {
                //prints a row of cells
                rowToPrint += this.TABLE[i][j];
            }
            System.out.printf("%5s", rowToPrint + "\n");
        }

    }

    //sets the diff level based on user input
    private int setDifficultyLvl(char difficultyLvlCharParam){
        switch (difficultyLvlCharParam) {
            case 'E':
                return 1;
            case 'M':
                return 2;
            case 'H':
                return 3;
            default:
                return -1;

        }
    }

    public int setN(int inputNParam) {
        return inputNParam;
    }

    private int setNumberOfMines(){
        int numberOfMines = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++) {
                if(this.TABLE[i][j].getIsMine()){
                    numberOfMines++;
                }
            }
        }
    return numberOfMines;
    }

    public Cell[][] getTABLE() {
        return this.TABLE;
    }

    public int getDifficultyLvl() {
        return this.difficultyLvl;
    }

    public int getN() {
        return this.N;
    }

    public int getNumberOfMines(){
        return this.numberOfMines;
    }


}
