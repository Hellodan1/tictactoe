module tictactoe {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens tictactoe to javafx.fxml;
    exports tictactoe;
}
