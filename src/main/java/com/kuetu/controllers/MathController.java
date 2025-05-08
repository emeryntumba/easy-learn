package com.kuetu.controllers;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import com.kuetu.App;

public class MathController extends AnchorPane implements Initializable {

    public int x, y;

    @FXML
    AnchorPane anchorMath;

    @FXML
    HBox vboxOperation1; // HBox pour le premier nombre

    @FXML
    HBox vboxOperation2;

    @FXML
    HBox hboxOperation; //HBox pour l'opération

    @FXML
    Label symbol;

    @FXML
    Button answerOption1;

    @FXML
    Button answerOption2;

    @FXML
    Button answerOption3;

    @FXML
    ImageView element;

    @FXML
    Button btnBack;

    private int score = 0;
    private int questionCount = 0;
    private int correctAnswer;
    private String currentOperation;
    private Random random = new Random();
    private List<Integer> answerOptions = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.onMouseDragged();
        this.onMousePressed();
        generateQuestion();
    }

    public void onMouseDragged(){
        anchorMath.setOnMouseDragged(event -> {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setX(event.getScreenX()-x);
            stage.setY(event.getScreenY()-y);
        });
    }

    public void onMousePressed(){
        anchorMath.setOnMousePressed(event -> {
            x = (int) event.getSceneX();
            y = (int) event.getSceneY();
        });
    }

    private void generateQuestion() {
        if (questionCount >= 5) {
            // Afficher le score final et éventuellement revenir à l'écran principal
            symbol.setText("Score: " + score + "/25");
            vboxOperation1.getChildren().clear();
            vboxOperation2.getChildren().clear();
            answerOption1.setVisible(false);
            answerOption2.setVisible(false);
            answerOption3.setVisible(false);
            // Calculate percentage and pass it to profile
            double percentage = (double) score / 25 * 100;
            navigateToProfile(percentage);
            return;
        }

        int num1 = random.nextInt(2) + 1;
        int num2 = random.nextInt(2) + 1;
        int operationType = random.nextInt(4); // 0: +, 1: -, 2: *, 3: /

        vboxOperation1.getChildren().clear(); // Effacer les images précédentes
        for (int i = 0; i < num1; i++) {
            ImageView imageView = new ImageView(element.getImage());
            imageView.setFitWidth(element.getFitWidth());
            imageView.setFitHeight(element.getFitHeight());
            vboxOperation1.getChildren().add(imageView);
        }

        currentOperation = getOperationSymbol(operationType);
        symbol.setText(currentOperation);

        vboxOperation2.getChildren().clear(); // Effacer les images précédentes
        for (int i = 0; i < num2; i++) {
            ImageView imageView = new ImageView(element.getImage());
            imageView.setFitWidth(element.getFitWidth());
            imageView.setFitHeight(element.getFitHeight());
            vboxOperation2.getChildren().add(imageView);
        }

        switch (operationType) {
            case 0: // Addition
                correctAnswer = num1 + num2;
                break;
            case 1: // Soustraction
                correctAnswer = num1 - num2;
                break;
            case 2: // Multiplication
                correctAnswer = num1 * num2;
                break;
            case 3: // Division
                correctAnswer = num1 / num2;
                break;
        }

        answerOptions.clear();
        answerOptions.add(correctAnswer);
        while (answerOptions.size() < 3) {
            int wrongAnswer = random.nextInt(10) + 1; // Nombre entre 1 et 10
            if (!answerOptions.contains(wrongAnswer)) {
                answerOptions.add(wrongAnswer);
            }
        }
        java.util.Collections.shuffle(answerOptions);

        answerOption1.setText(String.valueOf(answerOptions.get(0)));
        answerOption2.setText(String.valueOf(answerOptions.get(1)));
        answerOption3.setText(String.valueOf(answerOptions.get(2)));

        questionCount++;
    }

    private String getOperationSymbol(int operationType) {
        switch (operationType) {
            case 0:
                return "+";
            case 1:
                return "-";
            case 2:
                return "*";
            case 3:
                return "/";
            default:
                return "+";
        }
    }

    @FXML
    public void checkAnswer(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        int chosenAnswer = Integer.parseInt(clickedButton.getText());

        if (chosenAnswer == correctAnswer) {
            score += 5;
            clickedButton.setStyle("-fx-background-color: green;");
        } else {
            clickedButton.setStyle("-fx-background-color: red;");
        }

        // Pause avant de passer à la question suivante
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e -> {
            clickedButton.setStyle(""); // Réinitialiser le style
            generateQuestion();
        });
        pause.play();
    }

    @FXML
    public void onBackButtonClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/views/lesson.fxml"));
            AnchorPane lessonPane = loader.load();
            Scene scene = new Scene(lessonPane);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void navigateToProfile(double mathPercentage) {
        App.mathPercentage = mathPercentage; // Store in App class
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/views/profile.fxml"));
            AnchorPane profilePane = loader.load();

            // Get the controller for the profile pane
            ProfileController profileController = loader.getController();

            // Set the math percentage in the profile controller
            profileController.setMathPercentage(mathPercentage);

            Scene scene = new Scene(profilePane);
            Stage stage = (Stage) anchorMath.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}