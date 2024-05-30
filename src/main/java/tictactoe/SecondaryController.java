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
    
    private ArrayList<Rectangle> hoverSquares = new ArrayList<Rectangle>();

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
        hoverSquares.add(hoverSqaure11);
        hoverSquares.add(hoverSqaure12);
        hoverSquares.add(hoverSqaure13);
        hoverSquares.add(hoverSqaure21);
        hoverSquares.add(hoverSqaure22);
        hoverSquares.add(hoverSqaure23);
        hoverSquares.add(hoverSqaure31);
        hoverSquares.add(hoverSqaure32);
        hoverSquares.add(hoverSqaure33);

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
            System.out.println("Width: " + newSceneWidth);
            for(int ctr = 0; ctr < 9; ctr++){
                hoverSquares.get(ctr).setWidth((double)newSceneWidth/3);
                System.out.println(ctr);
                screenX = (double)newSceneWidth;
            }
        }
    });
        gridPane.heightProperty().addListener(new ChangeListener<Number>() {
        @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
            System.out.println("Height: " + newSceneHeight);
            for(int ctr = 0; ctr < 9; ctr++){
                hoverSquares.get(ctr).setHeight((double)newSceneHeight/3);
                System.out.println(ctr);
                screenY = (double)newSceneHeight;
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
            //System.out.println("Mouse location is X: " + mouseX + " Y: " + mouseY);
        }
            
    });

    }

    //this is used to put a shadow on the box im hovering over
    @FXML
    private void changeOpacity() {
        int locationX;
        if (mouseX<screenX/3) {
            locationX=1;
        }
        else if (mouseX<screenX/3*2){
            locationX=2;
        }
        else {
            locationX=3;
        }
        int locationY;
        if(mouseY<screenY/3) {
            locationY = 0;
        }
        else if(mouseY<screenY/3*2) {
            locationY = 3;
        }
        else {
            locationY = 6;
        }
        for (int ctr = 0; ctr < 9; ctr++) {
            hoverSquares.get(ctr).setOpacity(0);
        }
        hoverSquares.get(locationX+locationY-1).setOpacity(0.2);
    }
}