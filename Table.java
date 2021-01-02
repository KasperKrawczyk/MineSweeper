public class Table {

    private final int N;
    private final int difficultyLvl;

    private final Cell[][] TABLE;



    public Table(int nParam, char difficultyLvlCharParam) {
        this.N = setN(nParam);
        this.difficultyLvl = setDifficultyLvl(difficultyLvlCharParam);
        this.TABLE = setTable(nParam);
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

    public void countAllMines() {
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++) {
                this.TABLE[i][j].setMinedNeighbours(countMines(i, j));
            }
        }
    }

    public void readCell(Cell cellParam) {

        if(cellParam.getIsMine()) {
            Game.endGame();
        } else if(!cellParam.getIsMine() && cellParam.getMinedNeighbours() == 0) {
            //
        } else if(!cellParam.getIsMine() && cellParam.getMinedNeighbours() != 0) {
            //
        }
    }

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
        if (j + 1 < this.TABLE[0].length && this.TABLE[i][j + 1].getIsMine()) {
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

    public Cell findCell(int inputRowParam, int inputColumnParam) {
        return this.TABLE[inputRowParam][inputColumnParam];
    }

    public void clickMinelessNeighbours(Cell chosenCellParam, Cell[][] tableParam) {
        int startRow = chosenCellParam.getRow();
        int startColumn = chosenCellParam.getColumn();

//        Cell[] startRowArray = TABLE[startRow];
//
//        int startRowFirstMined = -1;
//        int startRowLastMined = -1;
//
//        for(int i = 0; i < getN(); i++) {
//            if(startRowArray[i].getIsMine()) {
//                startRowFirstMined = i;
//                break;
//            }
//        }
//
//        for(int i = startRowArray.length - 1; i >= 0; i--) {
//            if(startRowArray[i].getIsMine()) {
//                startRowLastMined = i;
//                break;
//            }
//        }
//
//        for(int i = startRowFirstMined; i == startRowLastMined; i++) {
//            TABLE[startRow][i].setIsClicked();
//        }
//
//        //downwards of the chosen row
//        for(int i = startRow; i < getN() - 1; i++) {
//            int previousRow = i;
//            int currentRow = i + 1;
//
//
//
//        }



        //downwards of the chosen row
        for(int i = startRow; i < this.getN(); i++) {

            //rightwards of the chosen column
            for(int j = startColumn; j < this.getN(); j++) {
                if(!tableParam[i][j].getIsMine()) {
                    tableParam[i][j].setIsClicked();
                } else {
                    break;
                }
            }
            //leftwards of the chosen column
            for(int j = startColumn; j >= 0; j--) {
                if(!tableParam[i][j].getIsMine()) {
                    tableParam[i][j].setIsClicked();
                } else {
                    break;
                }
            }
        }

        //upwards of the chosen row
        for(int i = startRow; i >= 0; i--) {

            //rightwards of the chosen column
            for(int j = startColumn; j < this.getN(); j++) {
                if(!tableParam[i][j].getIsMine()) {
                    tableParam[i][j].setIsClicked();
                } else {
                    break;
                }
            }
            //leftwards of the chosen column
            for(int j = startColumn; j >= 0; j--) {
                if(!tableParam[i][j].getIsMine()) {
                    tableParam[i][j].setIsClicked();
                } else {
                    break;
                }
            }
        }

    }

    private boolean readPreviousRowDownwards(int currentRowParam, int previousRowParam) {
        int previousRow = previousRowParam;
        int currentRow = currentRowParam;


        int previousRowFirstMined = -1;
        int previousRowLastMined = -1;

        int currentRowFirstMined = -1;
        int currentRowLastMined = -1;

        Cell[] previousRowArray = TABLE[previousRow];
        Cell[] currentRowArray = TABLE[currentRow];

        for(int i = 0; i < getN(); i++) {
            if(previousRowArray[i].getIsMine()) {
                previousRowFirstMined = i;
                break;
            }
        }

        for(int i = previousRowArray.length - 1; i >= 0; i--) {
            if(previousRowArray[i].getIsMine()) {
                previousRowLastMined = i;
                break;
            }
        }
        for(int i = 0; i < getN(); i++) {
            if(currentRowArray[i].getIsMine()) {
                currentRowFirstMined = i;
                break;
            }
        }

        for(int i = currentRowArray.length - 1; i >= 0; i--) {
            if(currentRowArray[i].getIsMine()) {
                currentRowLastMined = i;
                break;
            }
        }

        if((currentRowFirstMined < previousRowFirstMined && currentRowFirstMined < previousRowLastMined)
            || (currentRowFirstMined > previousRowLastMined && currentRowLastMined > previousRowLastMined)) {
            return false;
        } else {
            return true;
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

    public Cell[][] getTABLE() {
        return this.TABLE;
    }

    public int getDifficultyLvl() {
        return this.difficultyLvl;
    }

    public int getN() {
        return this.N;
    }


}
