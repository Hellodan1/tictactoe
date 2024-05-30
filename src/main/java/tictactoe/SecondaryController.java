package tictactoe;

import java.io.IOException;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class SecondaryController {

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


    //probably need to change this
    private Rectangle[][] hoverSquares =  new Rectangle[][] { {hoverSqaure11, hoverSqaure12, hoverSqaure13},
                                                              {hoverSqaure21, hoverSqaure22, hoverSqaure23},
                                                              {hoverSqaure31, hoverSqaure32, hoverSqaure33},
                                                            };

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("menu");
    }

    @FXML
    private void initialize(){
        
        //this binds the size of the game board to the size of the window
        lineX1.endXProperty().bind(gridPane.widthProperty());
        lineX2.endXProperty().bind(gridPane.widthProperty());
        lineY1.endYProperty().bind(gridPane.heightProperty());
        lineY2.endYProperty().bind(gridPane.heightProperty());
        upperBar.widthProperty().bind(gridPane.widthProperty());

        hoverSqaure11.opacityProperty().set(0.5);

        //see if i can modify this shit later so it isnt that weird number data type
        gridPane.widthProperty().addListener((ChangeListener<? super Number>) new ChangeListener<Number>() {
        @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
            System.out.println("Width: " + newSceneWidth);
            for(int ctr = 0; ctr < 3; ctr++){
                for(int ctr2 = 0; ctr2 < 3; ctr2++) {
                    hoverSquares[ctr][ctr2].setWidth((double)newSceneWidth/3);
                    System.out.println(ctr + " " + ctr2);
                }
            }
            System.out.println(hoverSquares[0][0]);
            hoverSquares[2][2].setWidth((double)newSceneWidth/3);
        }
    });
        gridPane.heightProperty().addListener(new ChangeListener<Number>() {
        @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
            System.out.println("Height: " + newSceneHeight);
            for(int ctr = 0; ctr < 3; ctr++){
                for(int ctr2 = 0; ctr2 < 3; ctr2++) {
                    hoverSquares[ctr][ctr2].setHeight((double)newSceneHeight/3);
                    System.out.println(ctr + " " + ctr2);
                }
            }
            System.out.println(hoverSquares[0][0]);
            hoverSqaure33.setHeight((double)newSceneHeight/3);
        }
    });

    }

    @FXML
    private void changeOpacity() {

    }

}