package com.dinyad.zelda;

import javafx.application.Application;
import javafx.beans.value.ObservableDoubleValue;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;
public class Main extends Application {
    Panel gridCanvas;
    @Override
    public void start(Stage stage) throws IOException {
        gridCanvas = new Panel(720, 720);
        BorderPane borderPane = new BorderPane();
        ScrollPane scrollPane = new ScrollPane();
        Pane pane = new Pane(gridCanvas);
        scrollPane.setContent(pane);
        scrollPane.setMaxWidth(750);
        scrollPane.setMaxHeight(650);
        borderPane.setLeft(scrollPane);
        //scrollPane.setPannable(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        Scene scene = new Scene(borderPane);

        Panel pane1 = new Panel(100,600);
        pane1.setSideElements(0,4,0,0,false,10);


        borderPane.setRight(pane1);


        gridCanvas.setDefaultValues(scene,scrollPane,pane1);
        gridCanvas.start("cartes/carte3.txt");
        stage.setResizable(false); // fenêtre non redimensionable
        stage.setTitle("Mon Zelda");
        stage.centerOnScreen(); // centrage sur l'écran
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();

    }
}
