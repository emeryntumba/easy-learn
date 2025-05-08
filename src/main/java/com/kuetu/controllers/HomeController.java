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

public class HomeController implements Initializable {

    public int x, y;

    @FXML
    Button btnProfile;

    @FXML
    Button btnLearn;

    @FXML
    Button btnClose;

    @FXML
    AnchorPane anchorMain;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.onMouseDragged();
        this.onMousePressed();
    }

    public void onMouseDragged() {
        anchorMain.setOnMouseDragged(event -> {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });

    }

    public void onMousePressed() {
        anchorMain.setOnMousePressed(event -> {
            x = (int) event.getSceneX();
            y = (int) event.getSceneY();
        });
    }

    @FXML
    public void onProfileButtonClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/views/profile.fxml"));
            AnchorPane profilePane = loader.load();
            Scene scene = new Scene(profilePane);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onLearnButtonClicked(ActionEvent event) {
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

    @FXML
    public void onCloseButtonClicked(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
