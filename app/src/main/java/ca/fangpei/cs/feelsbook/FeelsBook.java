package ca.fangpei.cs.feelsbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import ca.fangpei.cs.feelsbook.Emotions.Anger;
import ca.fangpei.cs.feelsbook.Emotions.Fear;
import ca.fangpei.cs.feelsbook.Emotions.Joy;
import ca.fangpei.cs.feelsbook.Emotions.Love;
import ca.fangpei.cs.feelsbook.Emotions.Mood;
import ca.fangpei.cs.feelsbook.Emotions.Sadness;
import ca.fangpei.cs.feelsbook.Emotions.Surprise;
/*
*
* This is the MainActivity metho.
*
* */
public class FeelsBook extends Activity {

    public final static String FILENAME = "file.sav";
    private ArrayList<Mood> emotions = new ArrayList<Mood>();
    private Love love;
    private Joy joy;
    private Surprise surprise;
    private Anger anger;
    private Sadness sadness;
    private Fear fear;




    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        joy = new Joy(FeelsBook.this);
        love = new Love(FeelsBook.this);
        surprise = new Surprise(FeelsBook.this);
        anger = new Anger(FeelsBook.this);
        sadness = new Sadness(FeelsBook.this);
        fear = new Fear(FeelsBook.this);
        emotions=FileEditor.loadFromFile(FeelsBook.this, FILENAME,emotions);

/*
* The rest of code is ued for constructing 6 buttons, each bottuon is corresponding for a emotion/mood
* When user click button, the code will instanciate a instance of the mood, and then jump to AddEmotion
* class to add new emotion to record
*
* */



        Button joy_button = (Button) findViewById(R.id.joy);
        joy_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                joy.addCount(FeelsBook.this);
                joy.setDate(new Date());





                Intent intent = new Intent(FeelsBook.this, AddEmotion.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ca.ualberta.emotion.key", joy);

                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        Button surprise_button = (Button) findViewById(R.id.surprise);

        surprise_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                surprise.addCount(FeelsBook.this);
                surprise.setDate(new Date());


                Intent intent = new Intent(FeelsBook.this, AddEmotion.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ca.ualberta.emotion.key", surprise);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });


        Button love_button = (Button) findViewById(R.id.love);

        love_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                love.addCount(FeelsBook.this);
                love.setDate(new Date());



                Intent intent = new Intent(FeelsBook.this, AddEmotion.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ca.ualberta.emotion.key", love);

                intent.putExtras(bundle);

                startActivity(intent);
            }
        });


        final Button anger_button  = (Button) findViewById(R.id.anger);

        anger_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                anger.addCount(FeelsBook.this);
                anger.getCount();
                anger.setDate(new Date());



                Intent intent = new Intent(FeelsBook.this, AddEmotion.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ca.ualberta.emotion.key", anger);

                intent.putExtras(bundle);

                startActivity(intent);
            }
        });


        Button sadness_button = (Button) findViewById(R.id.sadness);

        sadness_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                sadness.addCount(FeelsBook.this);
                sadness.setDate(new Date());



                Intent intent = new Intent(FeelsBook.this, AddEmotion.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ca.ualberta.emotion.key", sadness);

                intent.putExtras(bundle);

                startActivity(intent);
            }
        });


        Button fear_button = (Button) findViewById(R.id.fear);

        fear_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                fear.addCount(FeelsBook.this);
                fear.setDate(new Date());


                Intent intent = new Intent(FeelsBook.this, AddEmotion.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ca.ualberta.emotion.key", fear);
                //intent.setClass(FeelsBook.this, AddEmotion.class)
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });





    }
/*
* following code constructing a menu(actionbar), which consisted of two parts, one is history,
* another is the statistic for emotions
*
*
* */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void view_history (MenuItem menu)
    {

        Intent intent = new Intent(FeelsBook.this,HistoryOperation.class);

        startActivity(intent);
    }


    public void view_statistic (MenuItem menu)
    {

        Intent intent = new Intent(FeelsBook.this,EmotionStatistic.class);

        startActivity(intent);
    }

}