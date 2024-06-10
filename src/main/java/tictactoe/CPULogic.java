package tictactoe;

import java.util.Random;

public class CPULogic {
    
    int difficultyLvl;
    
    public CPULogic(int cpuLevel) {
        difficultyLvl = cpuLevel;
    }

    public void runCPU(GameBoard board, int CPUPiece, int playerStartingPiece) {

        if(difficultyLvl == 1 ) {
            runEasy(board, CPUPiece);
        }

        if(difficultyLvl == 2) {
            runMedium(board, CPUPiece, playerStartingPiece);   
        }

    }

    private void runEasy(GameBoard board, int CPUPiece) {
        Random ran = new Random();
        boolean kill = false;
            do {
                int x = ran.nextInt(3);
                int y = ran.nextInt(3);
                if (board.checkSpace(x, y) == 0) {
                    board.placePiece(x, y, CPUPiece);
                    kill = true;
                }

            } while (!kill);
    }

    private void runMedium(GameBoard board, int CPUPiece, int playerStartingPiece) {
        boolean moved = false;

        //try to win vertical?
        for(int x = 0; x<3; x++){
            if(board.checkSpace(x, 0) + board.checkSpace(x, 1) + board.checkSpace(x, 2) == CPUPiece*2) {
                for (int ctr = 0; ctr < 3; ctr++) {
                    if(board.checkSpace(x, ctr) == 0) {
                        board.placePiece(x, ctr, CPUPiece);
                        moved = true;
                    }
                 }
             }
        }
        
        //try to win horizontal
        if(moved == false) {
            for(int y = 0; y<3; y++){
                if(board.checkSpace(0, y) + board.checkSpace(1, y) + board.checkSpace(2, y) == CPUPiece*2) {
                    for (int ctr = 0; ctr < 3; ctr++) {
                        if(board.checkSpace(ctr, y) == 0) {
                            board.placePiece(ctr, y, CPUPiece);
                            moved = true;
                        }
                    }
                }
            }
        }

        //try to win diagonal (top left to bottom right)
        if(moved == false) {
            if (board.checkSpace(0, 0) + board.checkSpace(1, 1) + board.checkSpace(2, 2) == CPUPiece*2) {
                for (int ctr = 0; ctr < 3; ctr++) {
                    if(board.checkSpace(ctr, ctr) == 0) {
                        board.placePiece(ctr, ctr, CPUPiece);
                        moved = true;
                    }
                }
            }
        }
        

        //try to win diagonal (bottom left to top right)
        if(moved == false) {
            if (board.checkSpace(0, 2) + board.checkSpace(1, 1) + board.checkSpace(2, 0) == CPUPiece*2) {
                for (int ctr = 0; ctr < 3; ctr++) {
                    if(board.checkSpace(ctr, 2-ctr) == 0) {
                        board.placePiece(ctr, 2-ctr, CPUPiece);
                        moved = true;
                    }
                }
            }
        }

        //try to block vertical
        if(moved == false) {
            for(int x = 0; x<3; x++){
                if(board.checkSpace(x, 0) + board.checkSpace(x, 1) + board.checkSpace(x, 2) == playerStartingPiece*2) {
                    for (int ctr = 0; ctr < 3; ctr++) {
                        if(board.checkSpace(x, ctr) == 0) {
                            board.placePiece(x, ctr, CPUPiece);
                            moved = true;
                        }
                    }
                }
            }
        }  
        
        //try to block horizontal
        if(moved == false) {
            for(int y = 0; y<3; y++){
                if(board.checkSpace(0, y) + board.checkSpace(1, y) + board.checkSpace(2, y) == playerStartingPiece*2) {
                    for (int ctr = 0; ctr < 3; ctr++) {
                        if(board.checkSpace(ctr, y) == 0) {
                            board.placePiece(ctr, y, CPUPiece);
                            moved = true;
                        }
                    }
                }
            }
        }

        //try to block diagonal (top left to bottom right)
        if(moved == false) {
            if (board.checkSpace(0, 0) + board.checkSpace(1, 1) + board.checkSpace(2, 2) == playerStartingPiece*2) {
                for (int ctr = 0; ctr < 3; ctr++) {
                    if(board.checkSpace(ctr, ctr) == 0) {
                        board.placePiece(ctr, ctr, CPUPiece);
                        moved = true;
                    }
                }
            }
        }
        

        //try to block diagonal (bottom left to top right)
        if(moved == false) {
            if (board.checkSpace(0, 2) + board.checkSpace(1, 1) + board.checkSpace(2, 0) == playerStartingPiece*2) {
                for (int ctr = 0; ctr < 3; ctr++) {
                    if(board.checkSpace(ctr, 2-ctr) == 0) {
                        board.placePiece(ctr, 2-ctr, CPUPiece);
                        moved = true;
                    }
                }
            }
        }

        //if it cant find a way to block or win it will just go randomly
        if (moved == false) {
            runEasy(board, CPUPiece);
        }
        
    }
}