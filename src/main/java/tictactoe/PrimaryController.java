package tictactoe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class PrimaryController {

    //0 is multiplayer 1 is singleplayer
    public static int mode = 0;

    //1 easiest, 2 medium, 3 impossible (hopefully)
    public static int difficulty = 1;

    //x is 1, o is 20 (random numbers i chose for the sake of making win detection simpler)
    public static int piece = 1;

    @FXML
    Rectangle backgroundRectangle = new Rectangle(); 
    @FXML
    GridPane parentPane;
    @FXML
    ChoiceBox<String> opponentSelect;
    @FXML
    ChoiceBox<String> difficultySelect;
    @FXML
    Text difficultyText;
    @FXML
    ChoiceBox<String> pieceSelect;
    @FXML
    Text pieceText;

    @FXML
    ImageView ticTacToeLogo;

    Image ticTacToeLogoPNG = new Image(getClass().getResourceAsStream("tictactoelogo.png"));

    @FXML
    private void initialize() {
    
        ticTacToeLogo.setImage(ticTacToeLogoPNG);

        //this binding stuff basically makes the wdith of the gradient in the bacgkround have the same dimensions as the window
        backgroundRectangle.widthProperty().bind(parentPane.widthProperty()); 
        backgroundRectangle.heightProperty().bind(parentPane.heightProperty());
        
        //this adds all the options to the mode choicebox
        List<String> opponentOptions = new ArrayList<String>();
        opponentOptions.add("Multiplayer");
        opponentOptions.add("CPU");
        opponentSelect.getItems().addAll(opponentOptions);
        opponentSelect.getSelectionModel().select(mode);

        //detects when the mode choicebox is changed
        opponentSelect.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number oldValue, Number newValue) {
                if ((int)newValue == 0) {
                    mode = 0;
                } else if ((int) newValue == 1) {
                    mode = 1;
                }
                difficultyVisibility();
            }
            
        });

        //this adds all the options to the difficulty choicebox
        List<String> difficultyOptions = new ArrayList<String>();
        difficultyOptions.add("Easy");
        difficultyOptions.add("Medium");
        //difficultyOptions.add("Impossible");
        difficultySelect.getItems().addAll(difficultyOptions);
        difficultySelect.getSelectionModel().select(difficulty-1);
        difficultyVisibility();

        //detects when the difficulty choicebox is changed
        difficultySelect.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number oldValue, Number newValue) {
                switch ((int)newValue) {
                    case 0:
                        difficulty = 1;
                        break;
                    case 1:
                        difficulty = 2;
                        break;
                    //case 2:
                    //    difficulty = 3;
                    //    break;
                }
            }
            
        });

        //this adds all the options to the starting piece choicebox
        List<String> pieceOptions = new ArrayList<String>();
        pieceOptions.add("X");
        pieceOptions.add("O");
        pieceSelect.getItems().addAll(pieceOptions);
        if(piece == 1) {
            pieceSelect.getSelectionModel().select(0);
        } else {
            pieceSelect.getSelectionModel().select(1);
        }
        

        //detects when the piece choicebox is changed
        pieceSelect.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number oldValue, Number newValue) {
                if ((int)newValue == 0) {
                    piece = 1;
                } else if ((int) newValue == 1) {
                    piece = 20;
                }
            }
            
        });
    }

    @FXML
    private void switchToSecondary() throws IOException{
        App.setRoot("game"); //sets the window to display the game scene
    }

    @FXML
    private void difficultyVisibility() {
        if (mode == 0) {
            difficultySelect.setVisible(false);
            difficultyText.setVisible(false);
            pieceSelect.setVisible(false);
            pieceText.setVisible(false);
        } else if (mode == 1) {
            difficultySelect.setVisible(true);
            difficultyText.setVisible(true);
            pieceSelect.setVisible(true);
            pieceText.setVisible(true);
        }
    }
}
