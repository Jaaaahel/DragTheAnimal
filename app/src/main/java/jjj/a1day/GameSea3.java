package jjj.a1day;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class GameSea3 extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    private ImageView shadow, choice1, choice2, choice3;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_sea_3);

        TextView TotalScore = (TextView) findViewById(R.id.txtScore);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        int score = sharedpreferences.getInt("Score", 0);
        TotalScore.setText(String.valueOf(score));

        ImageView star = (ImageView) findViewById(R.id.star1);
        star.setVisibility(View.INVISIBLE);
        ImageButton btnBack = (ImageButton) findViewById(R.id.btnBack);
        ImageButton btnNext = (ImageButton) findViewById(R.id.btnNext);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.entrance);
        mp.start();
        mp.setLooping(true);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                startActivity(new Intent(GameSea3.this, MainMenu.class));
                finish();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                startActivity(new Intent(GameSea3.this, GameFarm1.class));
                overridePendingTransition(R.anim.slide_left_to_right, R.anim.slide_right_to_left);
                finish();
            }
        });

        choice1 = (ImageView)findViewById(R.id.games_crab_item);
        choice2 = (ImageView)findViewById(R.id.games_sealion_item);
        choice3 = (ImageView)findViewById(R.id.games_turtle_item);

        //Views to drop unto

        shadow = (ImageView)findViewById(R.id.games_sealion_shadow);

        //set touch listener

        choice1.setOnTouchListener(new GameSea3.ChoiceTouchListener());
        choice2.setOnTouchListener(new GameSea3.ChoiceTouchListener());
        choice3.setOnTouchListener(new GameSea3.ChoiceTouchListener());
        //set drag listeners

        shadow.setOnDragListener(new GameSea3.ChoiceDragListener());

    }

    private final class ChoiceTouchListener implements View.OnTouchListener{
        public boolean onTouch(View view, MotionEvent motionEvent){
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                //setup drag
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

                //start dragging the item touched
                view.startDrag(data, shadowBuilder, view, 0);

                return true;
            }
            else {
                return false;
            }
        }
    }


    private class ChoiceDragListener implements View.OnDragListener{
        @Override
        public boolean onDrag(View v, DragEvent event) {
            //handle drag events
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DROP:
                    //handle the dragged view being dropped over a drop view
                    //handle the dragged view being dropped over a target view
                    View view = (View) event.getLocalState();
                    //stop displaying the view where it was before it was dragged
                    view.setVisibility(View.INVISIBLE);
                    //view dragged item is being dropped on
                    ImageView dropTarget = (ImageView) v;
                    //view being dragged and dropped
                    ImageView dropped = (ImageView) view;

                    String tagDropTarget = (String)dropTarget.getTag(),
                            tagDroppedImage = (String)dropped.getTag();

                    if ((tagDropTarget != null) && (tagDropTarget.equals (tagDroppedImage))) {

                        stopPlaying();
                        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        mp.start();
                        dropTarget.setImageDrawable(dropped.getDrawable());

                        ImageView star = (ImageView) findViewById(R.id.star1);
                        star.setVisibility(View.VISIBLE);
                        Animation animSlide = AnimationUtils.loadAnimation(getApplicationContext(),
                                R.anim.slide_star);
                        Animation animScale = AnimationUtils.loadAnimation(getApplicationContext(),
                                R.anim.scale_star);
                        AnimationSet s = new AnimationSet(false);//false means don't share interpolators
                        s.addAnimation(animScale);
                        s.addAnimation(animSlide);
                        star.startAnimation(s);
                        star.setVisibility(View.INVISIBLE);

                        final Handler handler1 = new Handler();
                        handler1.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                int score = sharedpreferences.getInt("Score", 0);
                                score += 1;
                                TextView TotalScore = (TextView) findViewById(R.id.txtScore);
                                TotalScore.setText(String.valueOf(score));

                                SharedPreferences.Editor editor = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE).edit();
                                editor.putInt("Score", score);
                                editor.apply();
                            }
                        }, 900);

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //startActivity(new Intent(GameSea3.this, GameSea4.class));
                                Intent newIntent = null;
                                Random rand = new Random();

                                int index = rand.nextInt(17);
                                switch (index) {
                                    case 0:
                                        newIntent = new Intent(GameSea3.this, GameFarm2.class);
                                        break;
                                    case 1:
                                        newIntent = new Intent(GameSea3.this, GameFarm3.class);
                                        break;
                                    case 2:
                                        newIntent = new Intent(GameSea3.this, GameFarm4.class);
                                        break;
                                    case 3:
                                        newIntent = new Intent(GameSea3.this, GameFarm5.class);
                                        break;
                                    case 4:
                                        newIntent = new Intent(GameSea3.this, GameFarm6.class);
                                        break;
                                    case 5:
                                        newIntent = new Intent(GameSea3.this, GameFarm1.class);
                                        break;
                                    case 6:
                                        newIntent = new Intent(GameSea3.this, GameJungle2.class);
                                        break;
                                    case 7:
                                        newIntent = new Intent(GameSea3.this, GameJungle3.class);
                                        break;
                                    case 8:
                                        newIntent = new Intent(GameSea3.this, GameJungle4.class);
                                        break;
                                    case 9:
                                        newIntent = new Intent(GameSea3.this, GameJungle5.class);
                                        break;
                                    case 10:
                                        newIntent = new Intent(GameSea3.this, GameJungle6.class);
                                        break;
                                    case 11:
                                        newIntent = new Intent(GameSea3.this, GameJungle1.class);
                                        break;
                                    case 12:
                                        newIntent = new Intent(GameSea3.this, GameSea2.class);
                                        break;
                                    case 13:
                                        newIntent = new Intent(GameSea3.this, GameSea1.class);
                                        break;
                                    case 14:
                                        newIntent = new Intent(GameSea3.this, GameSea4.class);
                                        break;
                                    case 15:
                                        newIntent = new Intent(GameSea3.this, GameSea5.class);
                                        break;
                                    case 16:
                                        newIntent = new Intent(GameSea3.this, GameSea6.class);
                                        break;
                                }

                                startActivity(newIntent);
                                finish();
                            }
                        }, 1600);

                    } else {
                        // oppps, wrong!!!!
                    }
                    break;

                case DragEvent.ACTION_DRAG_ENDED:
                    //no action necessary
                    break;

                default:
                    break;
            }
            return true;
        }
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
