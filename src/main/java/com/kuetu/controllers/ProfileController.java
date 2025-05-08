package com.kuetu.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ProfileController extends AnchorPane implements Initializable {

    public int x, y;

    @FXML
    AnchorPane anchorProfile;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.onMouseDragged();
        this.onMousePressed();
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


    
}
