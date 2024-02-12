package com.example.currencyconverterjavafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;


public class AppLauncher extends Application {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    @Override
    public void start(Stage stage) {
        Model model = new ModelImpl();
        Controller controller = new ControllerImpl(model);
        VBox layout = new VBox();
        HBox title_layout = new HBox();
        title_layout.setAlignment(Pos.CENTER);
        title_layout.setSpacing(10.0);
        Label title_label = new Label("Currency Converter");
        title_label.setFont(new Font("Arial", 24));
        title_layout.getChildren().addAll(title_label);
        HBox options_layout = new HBox();
        options_layout.setAlignment(Pos.CENTER);
        options_layout.setSpacing(10.0);
        options_layout.setFillHeight(true);
        VBox amount_box = new VBox();
        Label amount_label = new Label("Amount:");
        TextField amount_text_field = new TextField();
        amount_text_field.setPrefHeight(30.0);
        amount_text_field.setPromptText("Enter amount here");
        amount_box.getChildren().addAll(amount_label, amount_text_field);
        VBox from_box = new VBox();
        Label from_label = new Label("From:");
        Map<String, String> currencyMap = new HashMap<>();
        currencyMap.put("USD", "USD.png");
        currencyMap.put("EUR", "EUR.png");
        currencyMap.put("JPY", "JPY.png");
        currencyMap.put("GBP", "GBP.png");
        currencyMap.put("CNY", "CNY.png");
        currencyMap.put("AUD", "AUD.png");
        currencyMap.put("CAD", "CAD.png");
        currencyMap.put("CHF", "CHF.png");
        currencyMap.put("SGD", "SGD.png");
        ComboBox<String> from_combo_box = new ComboBox<>();
        from_combo_box.setCellFactory(param -> new ListCell<>() {
            private final ImageView imageView = new ImageView();
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                }
                else {
                    setText(item);
                    String imagePath = currencyMap.get(item);
                    if (imagePath != null) {
                        Image image = new Image(imagePath);
                        imageView.setImage(image);
                        setGraphic(imageView);
                        imageView.setPreserveRatio(true);
                        imageView.setFitWidth(15);
                        imageView.setFitHeight(15);
                    }
                }
            }
        });
        List<String> list = new ArrayList<>(currencyMap.keySet());
        Collections.sort(list);

        from_combo_box.getItems().addAll(list);

        from_combo_box.setPrefHeight(30);
        from_box.getChildren().addAll(from_label, from_combo_box);
        VBox to_box = new VBox();
        Label to_label = new Label("To:");

        ComboBox<String> to_combo_box = new ComboBox<>();
        to_combo_box.setCellFactory(param -> new ListCell<>() {
            private final ImageView imageView = new ImageView();
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                }
                else {
                    setText(item);
                    String imagePath = currencyMap.get(item);
                    if (imagePath != null) {
                        Image image = new Image(imagePath);
                        imageView.setImage(image);
                        setGraphic(imageView);
                        imageView.setPreserveRatio(true);
                        imageView.setFitWidth(15);
                        imageView.setFitHeight(15);
                    }
                }
            }
        });
        to_combo_box.getItems().addAll(list);
        to_combo_box.setPrefHeight(30.0);
        to_box.getChildren().addAll(to_label, to_combo_box);
        VBox convert_box = new VBox();
        Label convert_label = new Label("");
        Image imageConvert = new Image("Convert Image.png");
        ImageView view = new ImageView(imageConvert);
        view.setPreserveRatio(true);
        view.setFitHeight(20.0);
        view.setFitWidth(20.0);
        Button convert_button = new Button("Convert", view);
        convert_button.setPrefHeight(30);
        convert_button.setStyle("-fx-base: #b6e7c9");
        convert_box.getChildren().addAll(convert_label, convert_button);
        convert_box.setFillWidth(true);
        VBox result_box = new VBox();
        Label result_label = new Label();
        result_label.setAlignment(Pos.CENTER);
        result_box.setAlignment(Pos.CENTER);
        result_box.setSpacing(10.0);
        result_label.setFont(new Font("Arial", 14));

        convert_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Convert currencies
                if ((amount_text_field.getText() == null) || amount_text_field.getText().isEmpty()) {
                    result_label.setText("You have not entered an amount.");
                    result_label.setTextFill(Color.color(1, 0, 0));
                }
                else if (Double.parseDouble(amount_text_field.getText()) < 0) {
                    result_label.setText("Please enter a positive amount.");
                    result_label.setTextFill(Color.color(1, 0, 0));
                }
                else if (from_combo_box.getValue() == null || from_combo_box.getValue().isEmpty() || to_combo_box.getValue() == null || to_combo_box.getValue().isEmpty()) {
                    result_label.setText("Please pick wanted currencies");
                    result_label.setTextFill(Color.color(1, 0, 0));
                }
                else {
                    double amount = Double.parseDouble(amount_text_field.getText());
                    String from_currency = from_combo_box.getValue();
                    String to_currency = to_combo_box.getValue();
                    controller.update_model(amount, from_currency, to_currency);
                    try {
                        result_label.setText(df.format(amount) + " " + from_currency + " = " + df.format(controller.get_result()) + " " + to_currency);
                        result_label.setTextFill(Color.color(0, 0, 0));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        );
        options_layout.getChildren().addAll(amount_box, from_box, to_box, convert_box);
        layout.getChildren().addAll(title_layout, options_layout, result_label);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10.0);
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
