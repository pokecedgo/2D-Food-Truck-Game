package managetruckboba;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundHandler {
    
    private Clip backgroundClip;
    
    public void playSound(String soundFilePath) {
        try {
            File soundFile = new File(soundFilePath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);

            if (soundFilePath.equals("background_music.wav")) {
                backgroundClip = clip;  // Save the clip reference for pausing and resuming
                clip.loop(Clip.LOOP_CONTINUOUSLY);  // Loop the clip indefinitely
            } else {
                clip.start();
            }
            
            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP && clip != backgroundClip) {
                        event.getLine().close();
                    }
                }
            });
            
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    
    public void pauseBackgroundAudio() {
        if (backgroundClip != null && backgroundClip.isRunning()) {
            backgroundClip.stop();
        }
    }

}
