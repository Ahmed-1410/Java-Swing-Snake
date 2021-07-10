package dataManagers;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class DJ {
    public static final String BitePath = "Audio"+File.separator+"Bite.wav";
    public static final String BonkPath = "Audio"+File.separator+"Bonk.wav";
    public static final String AmongPath = "Audio"+File.separator+"Among.wav";
    public static final String WOWPath = "Audio"+File.separator+"WOW.wav";
    public static final String WiiPath = "Audio"+File.separator+"Wii.wav";
    public static final String IntroPath = "Audio"+File.separator+"Intro.wav";
    public static final String GameOverPath = "Audio"+File.separator+"Mario.wav";

    public static Clip clipPlaying;
    public static long trackPosition;

    public static Clip Play(String path) {
        File file = new File(path);
        Clip clip = null;
        FloatControl gainControl = null;
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

        if(!(path.equals(BitePath) || path.equals(BonkPath) || path.equals(WOWPath)))
            clipPlaying = clip;
        if(path.equals(WiiPath)) {
            assert gainControl != null;
            gainControl.setValue(-20.0f);
            clip.loop(100);
        }

        assert clip != null;
        clip.start();
        return clip;
    }

    public static void stop(){
        trackPosition = clipPlaying.getMicrosecondPosition();
        clipPlaying.stop();
    }

    public static void resume(){
        clipPlaying.setMicrosecondPosition(trackPosition);
        clipPlaying.start();
    }
}
