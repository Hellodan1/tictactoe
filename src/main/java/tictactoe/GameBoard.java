package tictactoe;

import java.util.ArrayList;

public class GameBoard {
    
    //Game board array. Zero is blank, 1 is x, 20 is O
    private ArrayList<ArrayList<Integer>> gameBoardOuter = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> gameBoardInner1 = new ArrayList<Integer>();
    private ArrayList<Integer> gameBoardInner2 = new ArrayList<Integer>();
    private ArrayList<Integer> gameBoardInner3 = new ArrayList<Integer>();

    public GameBoard() {
        for(int ctr = 0; ctr <3; ctr++) {
            gameBoardInner1.add(1);
            gameBoardInner2.add(0);
            gameBoardInner3.add(0);
        }
        
        gameBoardOuter.add(gameBoardInner1);
        gameBoardOuter.add(gameBoardInner2);
        gameBoardOuter.add(gameBoardInner3);
    }

    public void placePiece(int x, int y, int piece) {
        gameBoardOuter.get(y).set(x,piece);
    }

    public int checkSpace(int x, int y) {
        return gameBoardOuter.get(y).get(x);
    }

    public int winDetect() {

        //Horizontal dectection
        for(int ctr = 0; ctr<3; ctr++){
            if (gameBoardOuter.get(ctr).get(0)+gameBoardOuter.get(ctr).get(1)+gameBoardOuter.get(ctr).get(2) == 3) {
                return 1;
            } else if (gameBoardOuter.get(ctr).get(0)+gameBoardOuter.get(ctr).get(1)+gameBoardOuter.get(ctr).get(2) == 60) {
                return 20;
            }
        }

        //Vertical detection
        for(int ctr = 0; ctr<3; ctr++){
            if (gameBoardOuter.get(0).get(ctr)+gameBoardOuter.get(1).get(ctr)+gameBoardOuter.get(2).get(ctr) == 3) {
                return 1;
            } else if (gameBoardOuter.get(0).get(ctr)+gameBoardOuter.get(1).get(ctr)+gameBoardOuter.get(2).get(ctr) == 60) {
                return 20;
            }
        }

        //diagonal top left to bottom right detection
        if (gameBoardOuter.get(0).get(0)+gameBoardOuter.get(1).get(1)+gameBoardOuter.get(2).get(2) == 3) {
            return 1;
        } else if (gameBoardOuter.get(0).get(0)+gameBoardOuter.get(1).get(1)+gameBoardOuter.get(2).get(2) == 60) {
            return 20;
        }

        //diagonal bottom left to bottom right detection
        if (gameBoardOuter.get(2).get(0)+gameBoardOuter.get(1).get(1)+gameBoardOuter.get(0).get(2) == 3) {
            return 1;
        } else if (gameBoardOuter.get(2).get(0)+gameBoardOuter.get(1).get(1)+gameBoardOuter.get(0).get(2) == 60) {
            return 20;
        }  

        return 0;
    }

    public void resetGameBoard(){
        for(int ctr = 0; ctr <3; ctr++) {
            gameBoardInner1.set(ctr,0);
            gameBoardInner2.set(ctr,0);
            gameBoardInner3.set(ctr,0);
        }
    }
}
