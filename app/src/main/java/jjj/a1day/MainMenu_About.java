package jjj.a1day;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainMenu_About extends AppCompatActivity {

    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_about);

        stopPlaying();
        mp = MediaPlayer.create(getApplicationContext(), R.raw.entrance);
        mp.start();
        mp.setLooping(true);
        ImageButton btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.click);
                mp.start();
                startActivity(new Intent(MainMenu_About.this, MainMenu.class));
                finish();
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
