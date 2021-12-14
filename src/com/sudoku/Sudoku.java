package com.sudoku;



public class Sudoku {
    // !colore vincoli
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    // !Numero di celle 9 x 9
    private static final int N = 9;
    // scacchiera attuale con i valori interi
    private int[][] board;
    // rappresenta quali dei valori della scacchiera sono modificabli e quali no
    private boolean[][] start;

    // ? Costruttore
    public Sudoku() {

        board = new int[N][N];
        start = new boolean[N][N];

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                board[row][col] = 0;
                start[row][col] = true;
            }
        }
    }

    /**
     * @return String Width board
     */
    // ? metodo toString
    @Override
    public String toString() {
        String b = "";

        for (int row = 0; row < N; row++) {
            if (row == 3 || row == 6) {
                b += "\n";
            }
            for (int col = 0; col < N; col++) {
                if (start[row][col] == false) {
                    b += RED + board[row][col] + RESET;
                } else {
                    b += board[row][col];
                }
                if (col == 2 || col == 5) {
                    b += " ";
                }

            }
            b += "\n";
        }
        return b;
    }

    /**
     * @implNote inserisce il valore specificato @param value in @param row e @param
     *           col
     * @param row   riga 1 a 9
     * @param col   colonna da 1 a 9
     * @param value da 1 a 9
     */
    public void InsertInitial(int row, int col, int value) {
        // check validation row, col, value
        if (row <= 0 || row > 9 || col <= 0 || col > 9 || value <= 0 || value > 9) {
            System.out.println("Puoi inserire un valore compreso tra 1 e 9 ");
            return;
        }
        // check duplicate insert value
        if (start[row - 1][col - 1] == false) {
            System.out.println("il valore in " + row + " " + col + " é stato già inserito.");
            return;
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (r == row - 1 && c == col - 1) {
                    board[r][c] = value;
                    start[r][c] = false;
                }
            }
        }
    }

    public void InsertValue(int row, int col, int value) {
        // check validation row, col, value
        if (row <= 0 || row > 9 || col <= 0 || col > 9 || value <= 0 || value > 9) {
            System.out.println("Puoi inserire un valore compreso tra 1 e 9 ");
            return;
        }

        // check duplicate insert value
        if (start[row - 1][col - 1] == false) {
            System.out.println("il valore in " + row + " " + col + " é un vincolo tabella non può essere modificato.");
            return;
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (r == row - 1 && c == col - 1 && start[r][c] == true) {
                    board[r][c] = value;
                }
            }
        }

    }

    /**
     * 
     * @param row   riga del valore da controllare secondo regole sudoku
     * @param col   colonna del valore da controllare secondo regole sudoku
     * @param value valore da controllare secondo regole sudoku
     * @return
     */
    public boolean isValid(int row, int col, int value) {
        boolean res = false;
        // ? controllare se ci sia il valore duplicato in nei valori di row fissa e col
        // ? dinamica
        // ? controllare se ci sia il valore duplicato in nei valori di col fissa e row
        // ?dinamica
        // ? controllare se ci sia il valore duplicato in nei valori di matrice 3x3
           
        if (checkRow(row, value) && (checkColumn(col, value)) && checkSquare(row, col, value)) {
            res = true;
        }
        return res;
    }
    /**
     * 
     * @param row
     * @param col
     * @param value
     * @return true if square is valid in Sudoku rule
     */
    private boolean checkSquare(int row, int col, int value) {
        boolean res = true;
        int squareN = 3;
        int bRow = row-1;
        System.out.println("bRow: "+ bRow);
        int bCol = col -1;
        System.out.println("bCol: "+ bCol);
        int count=0;

        for(int r=0; r< squareN;r++){
            for(int c=0; c<squareN;c++){
               if(board[r][c]==value){
                System.out.println("board: "+ board[r][c] + " " + r +" "+ c);
                count++;
                if(count>=1){
                    res=false;
                }
               }
            }
        }
        System.out.println("checkSquare: "+ res);
        return res;
    }
    /**
     * 
     * @param row
     * @param value
     * @return true if row is valid in Sudoku rules
     */
    private boolean checkRow(int row, int value) {
        boolean res = true;
        for (int i = 0; i < N; i++) {
            if (board[row][i] == value) {
                res = false;
            }
        }
        System.out.println("checkRow: "+ res);
        return res;

    }
  /**
     * 
     * @param row
     * @param value
     * @return true if column is valid in Sudoku rules
     */
    private boolean checkColumn(int col, int value) {
        boolean res = true;
        for (int i = 0; i < N; i++) {
            if (board[i][col] == value) {
                res = false;
            }
        }
        System.out.println("checkCol: "+ res);
        return res;

    }
    /**
     * 
     * @return true se è pieno 
     */
    public boolean filled(){
        int count=0;
        for(int i=0; i< N;i++){
            for(int j=0; j<N;j++){
                if ( board[i][j] > 0){
                    count++;
                }
            }
        }

        return ((count == board.length) ? true : false);
    }

    public void resetBoard() {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                board[row][col] = 0;
                start[row][col] = true;
            }
        }

        System.out.println(this.toString());
    }
}
