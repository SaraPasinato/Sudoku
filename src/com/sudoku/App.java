package com.sudoku;
import java.util.Scanner;

public class App {
  
    private static Scanner s = new Scanner(System.in);
    private static Scanner user = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        Sudoku s= new Sudoku();
        //?inizializza i vincoli di gioco
       insertAllInitial(s);
        //!Giochiamo 
       insertValueChoice(s);
        //!Cancella gioco
       deleteBoard(s);
      
       
    }
    private static void deleteBoard(Sudoku game) {
        String res;
        Scanner clean=new Scanner(System.in);

        System.out.print("Vuoi cancellare il gioco ?(s,n):");
        res=clean.next();
       if(res.equals("s")){
           game.resetBoard();
           clean.close();
       }else{
           insertValueChoice(game);
       }

    }
    /**
     * initialize board Constrained for game 
     * @param game sudouku empty board
     */
    private static void insertAllInitial(Sudoku game){
        String res="s";
        int row,col,value=0;
        do{

            System.out.println("inserisci la riga del vincolo : ");
            row=s.nextInt();
            System.out.println("inserisci la colonna del vincolo : ");
            col=s.nextInt();
            System.out.println("inserisci il valore  : ");
            value=s.nextInt();
            
            game.InsertInitial(row, col, value);
            
            System.out.println("-------------------------");
            System.out.println(game);
            System.out.println("-------------------------");
            System.out.print("Vuoi inserire altri vincoli?(s,n):");
            res=s.next();
            
           


        }while("s".equalsIgnoreCase(res));
      
    }
    /**
     * inserisce i valori utenti avvertendo se il valore non è corretto
     * @param game
     */
    private static void insertValueChoice(Sudoku game){
        String res="s";
        int row,col,value=0;
        do{

            System.out.println("inserisci la riga del numero che vuoi inserire :");
            row=user.nextInt();
            System.out.println("inserisci la colonna del numero che vuoi inserire :");
            col=user.nextInt();
            System.out.println("inserisci il valore  :");
            value=user.nextInt();
            
           
            //System.out.println("il valore "+ value + " posto in ( "+ row + " , "+ col +") è: "+(game.isValid(row,col,value) ? "corretto": "sbagliato"));
            if(game.isValid(row, col, value)){
                game.InsertValue(row, col, value);
            }else{
                System.out.println("il valore "+ value + " posto in ( "+ row + " , "+ col +") è: "+(game.isValid(row,col,value) ? "corretto": "sbagliato"));
            }
            
            System.out.println("pieno : "+ game.filled());
            
            
            System.out.println("-------------------------");
            System.out.println(game);
            System.out.println("-------------------------");
            System.out.print("Vuoi inserire altri valori?(s,n):");
            res=user.next();
            
           


        }while("s".equalsIgnoreCase(res));

       

    }
}
