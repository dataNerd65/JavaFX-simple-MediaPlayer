package com.cozytheDEV;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MediaClass implements Initializable {

    @FXML
    private Label lblMediaTitle;

    @FXML
    private MediaView mainMediaView;

    @FXML
    private HBox mediaControlContainer;

    @FXML
    private Slider theVolumeSlider;

    @FXML
    private MenuBar menuBar;

    private MediaPlayer mediaPlayer;
    private Stage stage;

    @FXML
    public void openMediaFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home"), "Music"));
        fileChooser.setTitle("Select Media To Play");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Audio Files", "*.mp3"),
                new FileChooser.ExtensionFilter("Video Files", "*.mp4")
        );

        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            String mediaUrl = file.toURI().toString();
            System.out.println("Media file URL: " + mediaUrl);
            lblMediaTitle.setText(file.getName());

            try {
                Media media = new Media(mediaUrl);
                mediaPlayer = new MediaPlayer(media);
                System.out.println("Media player created successfully");

                mediaPlayer.setOnError(() -> {
                    System.out.println("Media error occurred: " + mediaPlayer.getError().getMessage());
                    showAlert("Media Error", mediaPlayer.getError().getMessage());
                });

                initializeMediaPlayer();
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error", "Failed to create MediaPlayer: " + e.getMessage());
            }
        } else {
            System.out.println("File is null!");
        }
    }

    private void initializeMediaPlayer() {
        if (mediaPlayer != null) {
            theVolumeSlider.setValue(mediaPlayer.getVolume() * 100);
            theVolumeSlider.valueProperty().addListener(observable -> mediaPlayer.setVolume(theVolumeSlider.getValue() / 100));
            mainMediaView.setMediaPlayer(mediaPlayer);

            mediaPlayer.setOnReady(() -> System.out.println("Media is ready to play"));
            mediaPlayer.setOnEndOfMedia(() -> System.out.println("End of media"));
        } else {
            System.out.println("Media player is null");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Select a media to play!");
    }

    @FXML
    private void play() {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        } else {
            showAlert("Information Dialog", "Media player not initialized. Please open a media file first.");
        }
    }

    @FXML
    private void pause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    @FXML
    private void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    @FXML
    private void restart() {
        if (mediaPlayer != null) {
            mediaPlayer.seek(mediaPlayer.getStartTime());
            mediaPlayer.play();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}


