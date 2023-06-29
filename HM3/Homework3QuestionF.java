package com.example.javahomeworkf;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;

public class Homework3QuestionF extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    Slider slider = new Slider();

// this is previous code
//    slider.valueProperty().addListener(new ChangeListener<Number>() {
//      @Override
//      public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
//        System.out.printf("The slider's new value is %s%n", newValue);
//      }
//    });

    //this one new one with lambda expression
    slider.valueProperty().addListener((obs, oldVal, newVal) -> System.out.printf("The slider's new value is %s%n", newVal));

    StackPane root = new StackPane(slider);
    Scene scene = new Scene(root, 300, 250);
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}