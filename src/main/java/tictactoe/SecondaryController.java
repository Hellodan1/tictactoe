package tictactoe;

import java.io.IOException;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class SecondaryController {

    GameBoard board = new GameBoard();

    CPULogic cpu = new CPULogic(PrimaryController.difficulty);

    int mode = PrimaryController.mode; //have it do all the mouse click stuff in an if lopp that checks if it is even or odd

    int playerStartingPiece = PrimaryController.piece;

    int CPUPiece;
    
    double mouseX = 0, mouseY = 0;
    double screenX = 640, screenY = 480;

    int currentTurn = 1;
    int xWinCount  = 0, oWinCount  = 0;
    int turnCount = 0;
    int win = 0;

    @FXML
    Line lineX1 = new Line();
    @FXML
    Line lineX2 = new Line();
    @FXML
    Line lineY1 = new Line();
    @FXML
    Line lineY2 = new Line();
    @FXML
    GridPane gridPane;
    @FXML
    Rectangle upperBar = new Rectangle();
    @FXML
    HBox upperBarHBox = new HBox();
    @FXML
    Text gameScore = new Text();
    @FXML 
    Rectangle bottomBar = new Rectangle();
    @FXML
    HBox bottomHBox = new HBox();
    @FXML
    Text topText = new Text();
    @FXML
    Button playAgainButton = new Button();

    //objects used for game pieces
    @FXML
    ImageView piece11 = new ImageView();
    @FXML
    ImageView piece12 = new ImageView();
    @FXML
    ImageView piece13 = new ImageView();
    @FXML
    ImageView piece21 = new ImageView();
    @FXML
    ImageView piece22 = new ImageView();
    @FXML
    ImageView piece23 = new ImageView();
    @FXML
    ImageView piece31 = new ImageView();
    @FXML
    ImageView piece32 = new ImageView();
    @FXML
    ImageView piece33 = new ImageView();
    Image xImage = new Image(getClass().getResourceAsStream("x.png"));
    Image oImage = new Image(getClass().getResourceAsStream("o.png"));


    //objects used for the hovering effect
    @FXML
    Rectangle hoverSqaure11 = new Rectangle();
    @FXML
    Rectangle hoverSqaure12 = new Rectangle();
    @FXML
    Rectangle hoverSqaure13 = new Rectangle();
    @FXML
    Rectangle hoverSqaure21 = new Rectangle();
    @FXML
    Rectangle hoverSqaure22 = new Rectangle();
    @FXML
    Rectangle hoverSqaure23 = new Rectangle();
    @FXML
    Rectangle hoverSqaure31 = new Rectangle();
    @FXML
    Rectangle hoverSqaure32 = new Rectangle();
    @FXML
    Rectangle hoverSqaure33 = new Rectangle();

    //Game piece array
    private ArrayList<ArrayList<ImageView>> gamePieceOuter = new ArrayList<ArrayList<ImageView>>();
    private ArrayList<ImageView> gamePieceInner1 = new ArrayList<ImageView>();
    private ArrayList<ImageView> gamePieceInner2 = new ArrayList<ImageView>();
    private ArrayList<ImageView> gamePieceInner3 = new ArrayList<ImageView>();

    //Hover effect array
    private ArrayList<ArrayList<Rectangle>> hoverSquaresOuter = new ArrayList<ArrayList<Rectangle>>();
    private ArrayList<Rectangle> hoverSquaresInner1 = new ArrayList<Rectangle>();
    private ArrayList<Rectangle> hoverSquaresInner2 = new ArrayList<Rectangle>();
    private ArrayList<Rectangle> hoverSquaresInner3 = new ArrayList<Rectangle>();

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("menu");
    }

    @FXML
    private void playAgain() throws IOException {
        reset();
        refreshBoard();
        if (mode == 1 && CPUPiece == currentTurn) {
            runRound();
        }
    }

    @FXML
    private void initialize(){
        
        reset();

        //adds all the hover squares into their corresponding arrays
        hoverSquaresInner1.add(hoverSqaure11);
        hoverSquaresInner1.add(hoverSqaure12);
        hoverSquaresInner1.add(hoverSqaure13);
        hoverSquaresInner2.add(hoverSqaure21);
        hoverSquaresInner2.add(hoverSqaure22);
        hoverSquaresInner2.add(hoverSqaure23);
        hoverSquaresInner3.add(hoverSqaure31);
        hoverSquaresInner3.add(hoverSqaure32);
        hoverSquaresInner3.add(hoverSqaure33);
        hoverSquaresOuter.add(hoverSquaresInner1);
        hoverSquaresOuter.add(hoverSquaresInner2);
        hoverSquaresOuter.add(hoverSquaresInner3);

        //sets the game piece array
        gamePieceInner1.add(piece11);
        gamePieceInner1.add(piece12);
        gamePieceInner1.add(piece13);
        gamePieceInner2.add(piece21);
        gamePieceInner2.add(piece22);
        gamePieceInner2.add(piece23);
        gamePieceInner3.add(piece31);
        gamePieceInner3.add(piece32);
        gamePieceInner3.add(piece33);
        gamePieceOuter.add(gamePieceInner1);
        gamePieceOuter.add(gamePieceInner2);
        gamePieceOuter.add(gamePieceInner3);


        //this binds the size of the game board to the size of the window
        lineX1.endXProperty().bind(gridPane.widthProperty());
        lineX2.endXProperty().bind(gridPane.widthProperty());
        lineY1.endYProperty().bind(gridPane.heightProperty());
        lineY2.endYProperty().bind(gridPane.heightProperty());
        upperBar.widthProperty().bind(gridPane.widthProperty());
        bottomBar.widthProperty().bind(gridPane.widthProperty());

        if (playerStartingPiece == 1) {
            CPUPiece = 20;
        } else {
            CPUPiece = 1;
        }

        //this is for dynamically(i think that is the right word) changing the width of the shadow rectangles and game pieces as the window grows
        gridPane.widthProperty().addListener((ChangeListener<? super Number>) new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                for(int ctr = 0; ctr < 3; ctr++){
                    for(int ctr2 = 0; ctr2<3; ctr2++) {
                        hoverSquaresOuter.get(ctr).get(ctr2).setWidth((double)newSceneWidth/3);
                        gamePieceOuter.get(ctr).get(ctr2).setFitWidth((double)newSceneWidth/3-20);
                        screenX = (double)newSceneWidth;
                        upperBarHBox.setPrefWidth((double)newSceneWidth);
                        bottomHBox.setPrefWidth((double)newSceneWidth);
                    }
                    
                }
            }
        });

        //this is for dynamically(i think that is the right word) changing the height of the shadow rectangles and game pieces as the window grows
        gridPane.heightProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                for(int ctr = 0; ctr < 3; ctr++){
                    for(int ctr2 = 0; ctr2<3; ctr2++) {
                        hoverSquaresOuter.get(ctr).get(ctr2).setHeight((double)newSceneHeight/3);
                        gamePieceOuter.get(ctr).get(ctr2).setFitHeight((double)newSceneHeight/3-20);
                        screenY = (double)newSceneHeight;
                    }
                }
            }
        });

        //listner for mouse location on the game grid! (this took way too long to figure out)
        gridPane.setOnMouseMoved(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                mouseX = event.getX();
                mouseY = event.getY();
                changeOpacity();
            }
                
        });

        gridPane.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent click) {
                if (playerStartingPiece == currentTurn || mode == 0) {
                    runRound();

                }
            }
        });

        if (mode == 1 && CPUPiece == 1) {
            runRound();
        }
    }

    //this is used to put a shadow on the box im hovering over
    @FXML
    private void changeOpacity() {
        for(int ctr = 0; ctr < 3; ctr++){
            for(int ctr2 = 0; ctr2<3; ctr2++) {
                hoverSquaresOuter.get(ctr).get(ctr2).setOpacity(0);
            }
        }
        if (board.winDetect() == 0 && turnCount != 9) {
            hoverSquaresOuter.get(mouseLocationFinderY()).get(mouseLocationFinderX()).setOpacity(0.2);
        }
    }

    //this method runs whenver the mouse clicks. it locates what square the mouse is in and sets it to the corresponding value if there isnt already a game piece in it
    private void runRound() {
        if (mode == 0) {
            if(board.checkSpace(mouseLocationFinderX(), mouseLocationFinderY()) == 0 && board.winDetect() == 0) {
                board.placePiece(mouseLocationFinderX(), mouseLocationFinderY(), currentTurn);
                turnCount++;
                winDetect();
                refreshBoard();
                turnSwap();
            }
        }
        
        if (mode == 1) {
            if (CPUPiece == currentTurn && board.winDetect() == 0 && turnCount < 9) {
                cpu.runCPU(board, CPUPiece, playerStartingPiece);
                turnCount++;
                winDetect();
                turnSwap();
            } else if (playerStartingPiece == currentTurn && board.winDetect() == 0 && turnCount < 9) {
                if(board.checkSpace(mouseLocationFinderX(), mouseLocationFinderY()) == 0) {
                    board.placePiece(mouseLocationFinderX(), mouseLocationFinderY(), currentTurn);
                    turnCount++;
                    winDetect();
                    turnSwap();
                }
            }
            
            refreshBoard();
            if (currentTurn == CPUPiece && turnCount < 9 && board.winDetect() == 0) {
                runRound();
            }
        }
        
        
        
    }
    
    //very basic and saves me from having to type this twice (literally one line saved but at least it looks cleaner)
    private void turnSwap() {
        if (currentTurn == 1) {
            currentTurn = 20;
        } else if (currentTurn == 20 ) {
            currentTurn = 1;
        }
    }

    //find the square hat the mouse is in
    private int mouseLocationFinderX() {
        if (mouseX<screenX/3) {
            return 0;
        }
        else if (mouseX<screenX/3*2){
            return 1;
        }
        else {
            return 2;
        }
    }
    private int mouseLocationFinderY() {
        if(mouseY<screenY/3) {
            return 0;
        }
        else if(mouseY<screenY/3*2) {
            return 1;
        }
        else {
            return 2;
        }
    }
    
    @FXML //it will set the image roots depending on what it sees in the gameboard arraw
    private void refreshBoard(){
        for (int ctr = 0; ctr < 3; ctr++) {
            for (int ctr2 = 0; ctr2 < 3; ctr2++) {
                if (board.checkSpace(ctr2, ctr) == 1) {
                    gamePieceOuter.get(ctr).get(ctr2).setImage(xImage);
                }
                else if (board.checkSpace(ctr2, ctr) == 20) {
                    gamePieceOuter.get(ctr).get(ctr2).setImage(oImage);
                }
                else if (board.checkSpace(ctr2, ctr) == 0) {
                    gamePieceOuter.get(ctr).get(ctr2).setImage(null);
                }

            }
        }
        gameScore.setText(xWinCount + " - " + oWinCount);
    }

    private boolean winDetect() {
        if (board.winDetect() == 1) {
            xWinCount++;
            topText.setText("X Wins!");
            return true;
        } else if (board.winDetect() == 20) {
            oWinCount++;
            topText.setText("O Wins!");
            return true;
        } else if (turnCount == 9 && board.winDetect() == 0) {
            topText.setText("Tie!");
            return true;
        }
        return false;
    }

    //resets everything
    private void reset(){
        
        if (mode == 1 && CPUPiece == currentTurn) {
            runRound();
        }
        
        board.resetGameBoard();
        currentTurn = 1;
        topText.setText("Tic Tac Toe");
        turnCount = 0;
    }
}