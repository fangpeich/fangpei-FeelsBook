package ca.fangpei.cs.feelsbook;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ca.fangpei.cs.feelsbook.Emotions.Anger;
import ca.fangpei.cs.feelsbook.Emotions.Fear;
import ca.fangpei.cs.feelsbook.Emotions.Joy;
import ca.fangpei.cs.feelsbook.Emotions.Love;
import ca.fangpei.cs.feelsbook.Emotions.Sadness;
import ca.fangpei.cs.feelsbook.Emotions.Surprise;

public class EmotionStatistic extends Activity {
    private Love love;
    private Joy joy;
    private Surprise surprise;
    private Anger anger;
    private Sadness sadness;
    private Fear fear;
    private int count;
    private TextView joy_textView;
    private TextView love_textView;
    private TextView anger_textView;
    private TextView surprise_textView;
    private TextView fear_textView;
    private TextView sadness_textView;
    private String count_inf;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistic_count);

        joy = new Joy(EmotionStatistic.this);
        love = new Love(EmotionStatistic.this);
        surprise = new Surprise(EmotionStatistic.this);
        anger = new Anger(EmotionStatistic.this);
        sadness = new Sadness(EmotionStatistic.this);
        fear = new Fear(EmotionStatistic.this);

        joy_textView = (TextView)findViewById(R.id.joy_count);
        love_textView = (TextView)findViewById(R.id.love_count);
        anger_textView = (TextView)findViewById(R.id.anger_count);
        surprise_textView = (TextView)findViewById(R.id.surprise_count);
        fear_textView = (TextView)findViewById(R.id.fear_count);
        sadness_textView = (TextView)findViewById(R.id.sadness_count);

        Button backButton = (Button) findViewById(R.id.static_back);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



                count = joy.getJoyCount(EmotionStatistic.this,"JOY.PREFERENCE_FILE_KEY");
                joy_textView.setText(get_count_inf(joy.getName(), count));


                count = love.getLove_count(EmotionStatistic.this,"LOVE.PREFERENCE_FILE_KEY");
                love_textView.setText(get_count_inf(love.getName(), count));


                count = surprise.getSurprise_count(EmotionStatistic.this,"SURPRISE.PREFERENCE_FILE_KEY");
                surprise_textView.setText(get_count_inf(surprise.getName(), count));


                count = anger.getAnger_count(EmotionStatistic.this,"ANGER.PREFERENCE_FILE_KEY");
                anger_textView.setText(get_count_inf(anger.getName(), count));



                count = sadness.getSadness_count(EmotionStatistic.this,"SADNESS.PREFERENCE_FILE_KEY");
                sadness_textView.setText(get_count_inf(sadness.getName(), count));

                count = fear.getFear_count(EmotionStatistic.this,"FEAR.PREFERENCE_FILE_KEY");
                fear_textView.setText(get_count_inf(fear.getName(), count));

    }

    private String get_count_inf (String emotion_name, int count)
    {
        return "You felt "+emotion_name+ " "+ String.valueOf(count) + " " + "times";
    }
}
