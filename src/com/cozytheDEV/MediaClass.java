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

    private MediaPlayer mediaPlayer;
    private Stage stage;

    @FXML
    private MenuBar menuBar;

    @FXML
    public void openMediaFile() {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Select Media To Play");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Audio Files", "*mp3"),
                new FileChooser.ExtensionFilter("Audio Files", "*m4a"),
                new FileChooser.ExtensionFilter("Video Files", "*.mp4")
        );

        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            String MEDIA_URL = file.toString();
            lblMediaTitle.setText(file.getName());

            Media media = new Media(new File(MEDIA_URL).toURI().toString());

            mediaPlayer = new MediaPlayer(media);

            theVolumeSlider.setValue(mediaPlayer.getVolume() * 100);
            theVolumeSlider.valueProperty().addListener(observable -> mediaPlayer.setVolume(theVolumeSlider.getValue() / 100));

            mainMediaView.setMediaPlayer(mediaPlayer);
        }
    }

    @Override
    public void initialize(URL Location, ResourceBundle resources) {
        if (mediaPlayer != null) {
            MediaControl mediaControl = new MediaControl(mediaPlayer);
            mediaControlContainer.getChildren().add(mediaControl);
        } else {
            System.out.println("Select a media to play!");
        }
    }

    @FXML
    private void play(){
        if(mediaPlayer !=null){
            mediaPlayer.play();
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            System.out.println("Media player not initialized. Please open a media file first.");
            alert.setContentText("Media player not initialized. Please open a media file first.");

            alert.showAndWait();
        }
    }

    @FXML
    private void pause(){
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    @FXML
    private void stop(){
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    @FXML
    private void restart(){
        if (mediaPlayer != null) {
            mediaPlayer.seek(mediaPlayer.getStartTime());
            mediaPlayer.play();
        }
    }
}