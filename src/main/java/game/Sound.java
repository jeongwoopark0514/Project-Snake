package game;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import lombok.Getter;
import lombok.Setter;


/**
 * This class is for making sound.
 * If you put a correct fileName for the parameter, that file will be played.
 */
public class Sound {
    @Getter
    @Setter
    private File clap;
    @Getter
    @Setter
    private Clip clip;

    /**
     * Constructor for a sound object.
     *
     * @param fileName the name of the sound.
     */
    public Sound(String fileName) {
        this.clap = new File(fileName);
    }

    /**
     * Plays the audio file.
     *
     * @throws LineUnavailableException      if a clip object is
     *                                       not available due to resource restrictions.
     * @throws IOException                   Exception for file input stream.
     * @throws UnsupportedAudioFileException If the audio file is not supported.
     */
    public void play() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        clip = AudioSystem.getClip();
        clip.setFramePosition(0);
        clip.open(AudioSystem.getAudioInputStream(clap));
        clip.start();
    }
}
