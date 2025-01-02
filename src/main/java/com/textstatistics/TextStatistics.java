package com.textstatistics;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class TextStatistics extends Application {
    private Label lettersValue;
    private Label wordCountValue;
    private Label longestWordValue;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Text Statistics");

        Label name = new Label("Text Statistics");
        name.setAlignment(Pos.CENTER);
        name.setMaxWidth(Double.MAX_VALUE);
        name.setFont(new Font("Aria", 24));

        GridPane componentGroup = new GridPane();
        componentGroup.getStyleClass().add("view");
        componentGroup.setPadding(new Insets(20));
        componentGroup.setHgap(10);

        VBox vbox = new VBox();
        vbox.setSpacing(10);

        TextArea textArea = createTextArea();
        FlowPane outputComponentGroup = createOutputComponentGroup();

        textArea.textProperty().addListener((changed, oldValue, newValue) -> {
            int character = newValue.length();
            String[] parts = newValue.split(" ");
            int words = parts.length;
            String longest = Arrays.stream(parts).min((s1, s2) -> s2.length() - s1.length())
                    .get();

            lettersValue.setText(String.valueOf(character));
            wordCountValue.setText(String.valueOf(words));
            longestWordValue.setText(longest);
        });

        vbox.getChildren().addAll(name, textArea, outputComponentGroup);
        componentGroup.getChildren().add(vbox);

        int APP_WIDTH = 500;
        int APP_HEIGHT = 350;
        Scene scene = new Scene(componentGroup, APP_WIDTH, APP_HEIGHT);
        scene.getStylesheets().add(Objects.requireNonNull(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm()));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    private TextArea createTextArea() {
        TextArea textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setFont(new Font("Aria", 14));
        textArea.getStyleClass().add("text-area");
        textArea.setPromptText("Start typing...");
        return textArea;
    }

    private FlowPane createOutputComponentGroup() {
        FlowPane componentGroup = new FlowPane();
        componentGroup.setHgap(10);

        Label letters = new Label("Letters: ");
        Label wordCount = new Label("Words: ");
        Label longestWord = new Label("The longest word is: ");

        // add styling class
        letters.getStyleClass().add("output-group");
        wordCount.getStyleClass().add("output-group");
        longestWord.getStyleClass().add("output-group");

        lettersValue = new Label();
        wordCountValue = new Label();
        longestWordValue = new Label();
        longestWordValue.setWrapText(true);

        HBox letterGroup = new HBox();
        letterGroup.getChildren().addAll(letters, lettersValue);
        HBox wordGroup = new HBox();
        wordGroup.getChildren().addAll(wordCount, wordCountValue);

        HBox longestGroup = new HBox();
        longestGroup.getChildren().addAll(longestWord, longestWordValue);

        componentGroup.getChildren().addAll(letterGroup, wordGroup, longestGroup);

        return componentGroup;
    }

    public static void main(String[] args) {
        launch();
    }
}