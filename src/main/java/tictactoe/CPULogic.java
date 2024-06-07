package tictactoe;

import java.util.Random;

public class CPULogic {
    
    int difficultyLvl;

    public CPULogic(int cpuLevel) {
        difficultyLvl = cpuLevel;
    }

    public void runCPU(GameBoard board, int CPUPiece) {
        Random ran = new Random();
        if(difficultyLvl == 1 ) {
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
    }

}