package tictactoe;

import java.io.IOException;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class SecondaryController {

    double mouseX = 0, mouseY = 0;
    double screenX = 640, screenY = 480;

    int currentTurn = 1;

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

    //Game board array. Zero is blank, 1 is x, 20 is O
    private ArrayList<ArrayList<Integer>> gameBoardOuter = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> gameBoardInner1 = new ArrayList<Integer>();
    private ArrayList<Integer> gameBoardInner2 = new ArrayList<Integer>();
    private ArrayList<Integer> gameBoardInner3 = new ArrayList<Integer>();

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
    private void initialize(){
        
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
        
        for(int ctr = 0; ctr <3; ctr++) {
            gameBoardInner1.add(0);
            gameBoardInner2.add(0);
            gameBoardInner3.add(0);
        }
        
        gameBoardOuter.add(gameBoardInner1);
        gameBoardOuter.add(gameBoardInner2);
        gameBoardOuter.add(gameBoardInner3);

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

        //this is for dynamically(i think that is the right word) changing the width of the shadow rectangles and game pieces as the window grows
        gridPane.widthProperty().addListener((ChangeListener<? super Number>) new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                for(int ctr = 0; ctr < 3; ctr++){
                    for(int ctr2 = 0; ctr2<3; ctr2++) {
                        hoverSquaresOuter.get(ctr).get(ctr2).setWidth((double)newSceneWidth/3);
                        gamePieceOuter.get(ctr).get(ctr2).setFitWidth((double)newSceneWidth/3-20);
                        screenX = (double)newSceneWidth;
                        upperBarHBox.setPrefWidth((double)newSceneWidth);
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
            mouseClicked();
        }
    });

    }

    //this is used to put a shadow on the box im hovering over
    @FXML
    private void changeOpacity() {
        for(int ctr = 0; ctr < 3; ctr++){
            for(int ctr2 = 0; ctr2<3; ctr2++) {
                hoverSquaresOuter.get(ctr).get(ctr2).setOpacity(0);
            }
        }
        hoverSquaresOuter.get(mouseLocationFinderY()).get(mouseLocationFinderX()).setOpacity(0.2);
    }

    private void mouseClicked() {
        if (gameBoardOuter.get(mouseLocationFinderY()).get(mouseLocationFinderX()) == 0) {
            gameBoardOuter.get(mouseLocationFinderY()).set(mouseLocationFinderX(),currentTurn);
            refreshBoard();
            System.out.println(winDetect());
            if(currentTurn==1) {
                currentTurn = 20;
            } 
            else {
                currentTurn = 1;
            }
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
                if (gameBoardOuter.get(ctr).get(ctr2) == 1) {
                    gamePieceOuter.get(ctr).get(ctr2).setImage(xImage);
                }
                else if (gameBoardOuter.get(ctr).get(ctr2) == 20) {
                    gamePieceOuter.get(ctr).get(ctr2).setImage(oImage);
                }
            }
        }
    }

    private int winDetect() {
        int win = 0;
        //Horizontal dectection
        for(int ctr = 0; ctr<3; ctr++){
            if (gameBoardOuter.get(ctr).get(0)+gameBoardOuter.get(ctr).get(1)+gameBoardOuter.get(ctr).get(2) == 3) {
                System.out.println("X Wins");
                win = 1;
            } else if (gameBoardOuter.get(ctr).get(0)+gameBoardOuter.get(ctr).get(1)+gameBoardOuter.get(ctr).get(2) == 60) {
                System.out.println("O Wins");
                win = 20;
            }
        }

        //Vertical detection
        for(int ctr = 0; ctr<3; ctr++){
            if (gameBoardOuter.get(0).get(ctr)+gameBoardOuter.get(1).get(ctr)+gameBoardOuter.get(2).get(ctr) == 3) {
                System.out.println("X Wins");
                win = 1;
            } else if (gameBoardOuter.get(0).get(ctr)+gameBoardOuter.get(1).get(ctr)+gameBoardOuter.get(2).get(ctr) == 60) {
                System.out.println("O Wins");
                win = 20;
            }
        }

        //diagonal top left to bottom right detection
        if (gameBoardOuter.get(0).get(0)+gameBoardOuter.get(1).get(1)+gameBoardOuter.get(2).get(2) == 3) {
            System.out.println("X Wins diagonal 1");
            win = 1;
        } else if (gameBoardOuter.get(0).get(0)+gameBoardOuter.get(1).get(1)+gameBoardOuter.get(2).get(2) == 60) {
            System.out.println("O Wins diagonal 1");
            win = 20;
        }

        //diagonal bottom left to bottom right detection
        if (gameBoardOuter.get(2).get(0)+gameBoardOuter.get(1).get(1)+gameBoardOuter.get(0).get(2) == 3) {
            System.out.println("X Wins diagonal 2");
            win = 1;
        } else if (gameBoardOuter.get(2).get(0)+gameBoardOuter.get(1).get(1)+gameBoardOuter.get(0).get(2) == 60) {
            System.out.println("O Wins diagonal 2");
            win = 20;
        }  

        return win;
    }

    private void resetGameBoard(){
        for(int ctr = 0; ctr <3; ctr++) {
            gameBoardInner1.set(ctr,0);
            gameBoardInner2.set(ctr,0);
            gameBoardInner3.set(ctr,0);
        }
    }
}