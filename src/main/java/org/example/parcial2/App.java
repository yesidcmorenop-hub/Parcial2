package org.example.parcial2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    public App() {
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/parcial2/menu-principal.fxml"));
            Parent root = loader.load();

            primaryStage.setTitle("TechSolutions - Sistema de Gestión Tecnológica");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al cargar la ventana principal del menú.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
