package tictactoe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class PrimaryController {

    @FXML
    Rectangle backgroundRectangle = new Rectangle(); 
    @FXML
    GridPane parentPane;
    @FXML
    ChoiceBox<String> opponentSelect;

    @FXML
    private void initialize() {
        
        //this binding stuff basically makes the wdith of the gradient in the bacgkround have the same dimensions as the window
        backgroundRectangle.widthProperty().bind(parentPane.widthProperty()); 
        backgroundRectangle.heightProperty().bind(parentPane.heightProperty());
        
        //this adds all the options to the combobox on this menu
        List<String> opponentOptions = new ArrayList<String>();
        opponentOptions.add("Multiplayer");
        opponentOptions.add("CPU");
        opponentSelect.getItems().addAll(opponentOptions);
        opponentSelect.setValue("Multiplayer");
    }

    @FXML
    private void switchToSecondary() throws IOException{
        App.setRoot("game"); //sets the window to display the game scene
    }
}
