package gamarket;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import java.io.File;
import java.io.FileNotFoundException;


public class Soundtrack {

    private static Clip music;
    private static File musicPath;


    
    public static void loadMusic(String musicFileName) {
        try {
                musicPath = new File("./pokemon/soundtrack/" + musicFileName);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                music = AudioSystem.getClip();
                music.open(audioInput);
                FloatControl gainControl = (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-15.0f); // Reduce volume by 10 decibels.
			    System.out.println("Music is Playing.");
		    }
		   else 
		   {
			    System.out.println("Music Cannot be Played.");
		   } 
		   }catch(Exception e){
			   e.printStackTrace(); 
		   }
     }

     public static void startMusic()
     {
         music.start();
         music.loop(Clip.LOOP_CONTINUOUSLY);
     }

     public static void stopMusic()
     {
         music.stop(); 
     }
 }