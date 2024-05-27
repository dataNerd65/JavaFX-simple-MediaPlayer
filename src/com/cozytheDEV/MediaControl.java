package com.cozytheDEV;

import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.layout.Priority;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Region;
import javafx.scene.media.MediaPlayer;

public class MediaControl extends HBox {
    private MediaPlayer mediaPlayer;
    private Slider volumeSlider;
    private Label currentTime;
    private Button playButton;
    private Button pauseButton;
    private Button stopButton;

    public MediaControl(MediaPlayer mediaPlayer){
        volumeSlider = new Slider();
        volumeSlider.setPrefWidth(70);
        volumeSlider.setMaxWidth(Region.USE_PREF_SIZE);
        volumeSlider.setMinWidth(30);

        currentTime = new Label();
        currentTime.setText("00:00");

        playButton = new Button("Play");
        pauseButton = new Button("Pause");
        stopButton = new Button("Stop");

        this.getChildren().add(playButton);
        this.getChildren().add(pauseButton);
        this.getChildren().add(stopButton);
        this.getChildren().add(volumeSlider);
        this.getChildren().add(currentTime);

        HBox.setHgrow(volumeSlider, Priority.ALWAYS);

    }
    
}
