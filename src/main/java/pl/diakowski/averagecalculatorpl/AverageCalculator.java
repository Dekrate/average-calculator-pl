package pl.diakowski.averagecalculatorpl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AverageCalculator extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        VBox vbox = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainView.fxml")));
        Scene scene = new Scene(vbox);
        stage.setTitle("Kalkulator średniej ważonej");
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}