package com.kuetu.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.kuetu.App;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MathController extends AnchorPane implements Initializable {

    public int x,y;
    
    @FXML
    AnchorPane anchorMath;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.onMouseDragged();
        this.onMousePressed();
    }

    public MathController() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("views/math.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    
}
