package jjj.a1day;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainMenu extends AppCompatActivity {

    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        stopPlaying();
        Button btnStart = (Button) findViewById(R.id.btnStart);
        Button btnAbout = (Button) findViewById(R.id.btnAbout);
        TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.entrance);
        mp.start();
        mp.setLooping(true);
        final Animation animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        btnStart.startAnimation(animationFadeIn);
        btnAbout.startAnimation(animationFadeIn);
        txtTitle.startAnimation(animationFadeIn);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.click);
                mp.start();
                //startActivity(new Intent(MainMenu.this, GameFarm1.class));
                Intent newIntent = null;
                Random rand = new Random();

                int index = rand.nextInt(18);
                switch (index) {
                    case 0:
                        newIntent = new Intent(MainMenu.this, GameFarm1.class);
                        break;
                    case 1:
                        newIntent = new Intent(MainMenu.this, GameFarm2.class);
                        break;
                    case 2:
                        newIntent = new Intent(MainMenu.this, GameFarm3.class);
                        break;
                    case 3:
                        newIntent = new Intent(MainMenu.this, GameFarm4.class);
                        break;
                    case 4:
                        newIntent = new Intent(MainMenu.this, GameFarm5.class);
                        break;
                    case 5:
                        newIntent = new Intent(MainMenu.this, GameFarm6.class);
                        break;
                    case 6:
                        newIntent = new Intent(MainMenu.this, GameJungle1.class);
                        break;
                    case 7:
                        newIntent = new Intent(MainMenu.this, GameJungle2.class);
                        break;
                    case 8:
                        newIntent = new Intent(MainMenu.this, GameJungle3.class);
                        break;
                    case 9:
                        newIntent = new Intent(MainMenu.this, GameJungle4.class);
                        break;
                    case 10:
                        newIntent = new Intent(MainMenu.this, GameJungle5.class);
                        break;
                    case 11:
                        newIntent = new Intent(MainMenu.this, GameJungle6.class);
                        break;
                    case 12:
                        newIntent = new Intent(MainMenu.this, GameSea1.class);
                        break;
                    case 13:
                        newIntent = new Intent(MainMenu.this, GameSea2.class);
                        break;
                    case 14:
                        newIntent = new Intent(MainMenu.this, GameSea3.class);
                        break;
                    case 15:
                        newIntent = new Intent(MainMenu.this, GameSea4.class);
                        break;
                    case 16:
                        newIntent = new Intent(MainMenu.this, GameSea5.class);
                        break;
                    case 17:
                        newIntent = new Intent(MainMenu.this, GameSea6.class);
                        break;
                }

                startActivity(newIntent);
                finish();
            }
        });
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.click);
                mp.start();
                startActivity(new Intent(MainMenu.this, MainMenu_About.class));
                MainMenu.this.finish();
            }
        });
    }
    private void stopPlaying() {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        stopPlaying();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopPlaying();
    }
}
