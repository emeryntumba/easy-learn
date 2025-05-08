package com.kuetu;

/**
 * Hello world!
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Easy-Learn - Accueil");

        Text title = new Text("Bienvenue sur Easy-Learn !");
        Button playBtn = new Button("Jouer / Apprendre");
        Button profileBtn = new Button("Mon Profil");
        Button quitBtn = new Button("Quitter");

        playBtn.setOnAction(e -> System.out.println("→ Aller à Jouer / Apprendre"));
        profileBtn.setOnAction(e -> System.out.println("→ Aller à Mon Profil"));
        quitBtn.setOnAction(e -> primaryStage.close());

        VBox layout = new VBox(20, title, playBtn, profileBtn, quitBtn);
        layout.setStyle("-fx-padding: 40; -fx-alignment: center; -fx-spacing: 15;");

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
