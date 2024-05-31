module tictactoe {
    requires javafx.graphics;
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires java.desktop;

    opens tictactoe to javafx.fxml;
    exports tictactoe;
}
