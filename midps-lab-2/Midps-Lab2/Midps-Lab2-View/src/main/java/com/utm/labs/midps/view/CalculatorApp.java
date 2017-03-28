/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utm.labs.midps.view;

import java.io.File;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author andrei
 */
public class CalculatorApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        URL url = new File("src/main/java/com/utm/labs/midps/view/fxml/FXMLCalculator.fxml").toURL();
        Parent root = FXMLLoader.load(url);

        
        System.out.println("****RESOURCE");
        Scene scene = new Scene(root);
        System.out.println("****SCENE");
        stage.setScene(scene);
         System.out.println("****SET SCENE");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
