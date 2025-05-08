package com.kuetu.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.kuetu.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ProfileController extends AnchorPane implements Initializable {

    public int x, y;

    @FXML
    AnchorPane anchorProfile;

    @FXML
    Button btnBack;

    @FXML
    Label labelOrientation;

    @FXML
    HBox hboxMathStars;

    private double mathPercentage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.onMouseDragged();
        this.onMousePressed();

        // Retrieve orientation from App class
        if (App.orientation != null) {
            labelOrientation.setText(App.orientation);
        }

        // Retrieve math percentage from App class
        this.mathPercentage = App.mathPercentage;
        updateMathStars();
    }

    public void setMathPercentage(double percentage) {
        this.mathPercentage = percentage;
        updateMathStars();
        updateOrientationLabel();
    }

    private void updateMathStars() {
        hboxMathStars.getChildren().clear(); // Clear existing stars
        int numStars = (int) (mathPercentage / 20); // One star for every 20%
        for (int i = 0; i < numStars; i++) {
            ImageView star = new ImageView(new Image(getClass().getResourceAsStream("/media/img/star_127px.png")));
            star.setFitWidth(36.0);
            star.setFitHeight(36.0);
            hboxMathStars.getChildren().add(star);
        }
    }

    private void updateOrientationLabel() {
        if (mathPercentage >= 70) {
            App.orientation = "Math"; // Store in App class
            labelOrientation.setText("Math");
        } else {
            App.orientation = "Pas d'info, passez un autre test"; // Store in App class
            labelOrientation.setText("Pas d'info, passez un autre test");
        }
    }

    public void onMouseDragged(){
        anchorProfile.setOnMouseDragged(event -> {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setX(event.getScreenX()-x);
            stage.setY(event.getScreenY()-y);
        });

    }

    public void onMousePressed(){
        anchorProfile.setOnMousePressed(event -> {
            x = (int) event.getSceneX();
            y = (int) event.getSceneY();
        });
    }

    @FXML
    public void onBackButtonClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/views/home.fxml"));
            AnchorPane homePane = loader.load();
            Scene scene = new Scene(homePane);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}