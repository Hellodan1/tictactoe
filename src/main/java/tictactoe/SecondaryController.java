package tictactoe;

import java.io.IOException;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class SecondaryController {

    double mouseX = 0, mouseY = 0;
    double screenX = 640, screenY = 480;

    int currentTurn = 1;

    //Zero is blank, 1 is x, 2 is O. I know this would work better as a 2d array however i didn't want to make a 2d arraylist for the rectangles and I am reusing the mouse location method for this
    int[] gameBoard = {0,0,0,0,0,0,0,0,0};

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
    
    private ArrayList<ArrayList<Rectangle>> hoverSquaresOuter = new ArrayList<ArrayList<Rectangle>>();
    private ArrayList<Rectangle> hoverSquaresInner1 = new ArrayList<Rectangle>();
    private ArrayList<Rectangle> hoverSquaresInner2 = new ArrayList<Rectangle>();
    private ArrayList<Rectangle> hoverSquaresInner3 = new ArrayList<Rectangle>();

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

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("menu");
    }

    @FXML
    private void initialize(){
        
        //there is probably a better way to do this but it works and i want to work on more fun things
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

        //this binds the size of the game board to the size of the window
        lineX1.endXProperty().bind(gridPane.widthProperty());
        lineX2.endXProperty().bind(gridPane.widthProperty());
        lineY1.endYProperty().bind(gridPane.heightProperty());
        lineY2.endYProperty().bind(gridPane.heightProperty());
        upperBar.widthProperty().bind(gridPane.widthProperty());

        /* 
        for(int ctr = 0; ctr < 9; ctr++){
            hoverSquares.get(ctr).setOpacity(0.5);
        }
        */

        //see if i can modify this shit later so it isnt that weird number data type
        gridPane.widthProperty().addListener((ChangeListener<? super Number>) new ChangeListener<Number>() {
        @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
            for(int ctr = 0; ctr < 3; ctr++){
                for(int ctr2 = 0; ctr2<3; ctr2++) {
                    hoverSquaresOuter.get(ctr).get(ctr2).setWidth((double)newSceneWidth/3);
                    screenX = (double)newSceneWidth;
                }
                
            }
        }
    });
        gridPane.heightProperty().addListener(new ChangeListener<Number>() {
        @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
            for(int ctr = 0; ctr < 3; ctr++){
                for(int ctr2 = 0; ctr2<3; ctr2++) {
                    hoverSquaresOuter.get(ctr).get(ctr2).setHeight((double)newSceneHeight/3);
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
            //mouseClicked();
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

    /*
    private void mouseClicked() {
        if (gameBoard[mouseLocationFinder()] == 0) {
            gameBoard[mouseLocationFinder()] = currentTurn;
            if(currentTurn==1) {
                currentTurn = 2;
            } 
            else {
                currentTurn = 1;
            }
        }
        for(int ctr = 0; ctr < 9; ctr++) {
            System.out.println(gameBoard[ctr]);
        }
    }
    */

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
}