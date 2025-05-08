package com.kuetu.controllers;

import com.kuetu.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LessonController implements Initializable {

    public int x, y;

    @FXML
    AnchorPane anchorLesson;

    @FXML
    Button btnMath;

    @FXML
    Button btnFrench;

    @FXML
    Button btnBack;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.onMouseDragged();
        this.onMousePressed();
    }

    public void onMouseDragged() {
        anchorLesson.setOnMouseDragged(event -> {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });

    }

    public void onMousePressed() {
        anchorLesson.setOnMousePressed(event -> {
            x = (int) event.getSceneX();
            y = (int) event.getSceneY();
        });
    }

    @FXML
    public void onMathButtonClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/views/math.fxml"));
            AnchorPane mathPane = loader.load();
            Scene scene = new Scene(mathPane);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onFrenchButtonClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/views/french.fxml"));
            AnchorPane frenchPane = loader.load();
            Scene scene = new Scene(frenchPane);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
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