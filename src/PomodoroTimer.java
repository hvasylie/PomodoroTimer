import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.*;

public class PomodoroTimer {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Thread thread = new Thread(new Pause());

    static protected String string = "Beginning";

//    @Override
//    public void run() {
//        try {
//            timer();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    protected void timer () throws IOException {

        for (int minutes = 24; minutes >= 0; minutes--) {
            for (int seconds = 59; seconds >= 0; seconds--) {
                string = minutes + ":" + seconds;
                System.out.println(string);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (minutes == 0 && seconds == 0) {
                    playSound();
                    System.out.println("Press any key to start break, \"q\" to exit");
                    if (!reader.readLine().equals("q")) {
                        thread.start();
                        System.out.println("Press any key to end break, \"q\" to exit");
                        if (!reader.readLine().equals("q")) {
                            thread.interrupt();
                            minutes = 24;
                            seconds = 60;
                        } else return;
                    } else
                        return;

                }
            }
        }
    }

    private void pause(){
        try {
            Thread.sleep(1000);
            for (int mins = 4; mins >= 0; mins--) {
                for (int secs = 59; secs >= 0; secs--) {
                    string = mins + ":" + secs;
                    System.out.println(string);
                        Thread.sleep(1000);
                    if (mins == 0 && secs == 0) {
                        playSound();
                    }
                }
            }
            System.out.println("Press any key to start a short 25 minute work session, \"q\" to exit");
        } catch (InterruptedException e) {
            System.out.println("Get back to work homie");
        }
    }

    private class Pause implements Runnable{
        @Override
        public void run() {
            pause();
        }
    }

    private void playSound(){
        try {
            InputStream inputStream = new FileInputStream(new File("/Users/mymac/Desktop/PomodoroTimer/Sound effect/A-Tone-His_Self-1266414414.wav"));
            AudioStream audioStream = new AudioStream(inputStream);
            AudioPlayer.player.start(audioStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
