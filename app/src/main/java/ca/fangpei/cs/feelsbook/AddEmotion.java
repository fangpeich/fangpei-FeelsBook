package ca.fangpei.cs.feelsbook;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
* AddEmotion class is responsible for set subscription about current feeling
*
*
*
*
* */
public class AddEmotion extends Activity {


    private EditText bodyText;
    private TextView textView;
    private ArrayList<Mood> emotions = new ArrayList<Mood>();



    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor);
        textView = (TextView) findViewById(R.id.count);
        bodyText = (EditText) findViewById(R.id.body);

        //read the newest Mood ArraList from local file stored by json
        emotions = FileEditor.loadFromFile(AddEmotion.this, FeelsBook.FILENAME,emotions);

        // Emotion is a Mood object transfered by the FeelsBook Class
        final Mood emotion = (Mood) getIntent().getSerializableExtra("ca.ualberta.emotion.key");
        emotions.add(emotion);
        FileEditor.saveInFile(AddEmotion.this,FeelsBook.FILENAME,emotions);

        //First, for each emotion we need to show how many time the feeling has been recorded
        showWelcome(emotion,textView,AddEmotion.this);


        // The situation that user click the cancel button, simple back to FeelsBook
        Button cancelButton = (Button) findViewById(R.id.save_without_comment);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileEditor.saveInFile(AddEmotion.this, FeelsBook.FILENAME,emotions);
                finish();
            }
        });

        //The situation that user click save button, at this time we need to set messesge
        Button saveButton = (Button) findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEmotion(bodyText,FeelsBook.FILENAME, emotion, emotions, AddEmotion.this);



            }


        });

    }

    // method showWelcome identify which type the transfered obj is, then get corresponding count
    // and display it
    private void showWelcome(Mood emotion, TextView textView, Context context)
    {
        int count;
        switch (emotion.getEmotion_id()) {
            case 1:

                Joy joy = (Joy) emotion;
                count = joy.getJoyCount(AddEmotion.this,"JOY.PREFERENCE_FILE_KEY");
                textView.setText( "You just added a new joy record. You have " +
                        "added " + String.valueOf(count) +" times joy record. Would you like to write" +
                        "some coments ?");
                break;

            case 2:
                Love love = (Love) emotion;
                count = love.getLove_count(AddEmotion.this,"LOVE.PREFERENCE_FILE_KEY");
                textView.setText( "You just added a new love record. You have " +
                        "added " + String.valueOf(count) +" times joy record. Would you like to write" +
                        "some coments ?");
                break;
            case 3:

                Surprise surprise = (Surprise) emotion;
                count = surprise.getSurprise_count(AddEmotion.this,"SURPRISE.PREFERENCE_FILE_KEY");
                textView.setText( "You just added a new surprise record. You have " +
                        "added " + String.valueOf(count) +" times joy record. Would you like to write" +
                        "some coments ?");
                break;
            case 4:
                Anger anger = (Anger) emotion;
                count = anger.getAnger_count(AddEmotion.this,"ANGER.PREFERENCE_FILE_KEY");
                textView.setText( "You just added a new anger record. You have " +
                        "added " + String.valueOf(count) +" times joy record. Would you like to write" +
                        "some coments ?");
                break;
            case 5:

                Sadness sadness = (Sadness) emotion;
                count = sadness.getSadness_count(AddEmotion.this,"SADNESS.PREFERENCE_FILE_KEY");
                textView.setText( "You just added a new sadness record. You have " +
                        "added " + String.valueOf(count) +" times joy record. Would you like to write" +
                        "some coments ?");
                break;
            case 6:
                Fear fear = (Fear) emotion;
                count = fear.getFear_count(AddEmotion.this,"FEAR.PREFERENCE_FILE_KEY");
                textView.setText( "You just added a new fear record. You have " +
                        "added " + String.valueOf(count) +" times joy record. Would you like to write" +
                        "some coments ?");
                break;
        }
    }

    // addEmotion method is called when used input message, it will identify which type of mood the target obj is,
    // and then change the message attribute of corresponding obj in the mood arraylist
    private void addEmotion(EditText bodyText, String filename, Mood emotion, ArrayList<Mood> moods, Context context ) {

        String text ;
        int idx;
        if (bodyText.getText().length() > 100) {
            try {
                throw new SubscriptionTooLongException();

            } catch (SubscriptionTooLongException e) {
                Toast.makeText(context, "The message is too long! Please keep your tweets within 100 characters.", Toast.LENGTH_SHORT).show();

                e.printStackTrace();
            }

        } /*else if (text.equals("")) {
            finish();

        }*/ else {
            text = bodyText.getText().toString();


            switch (emotion.getEmotion_id()) {
                case 1:
                    try {
                        idx = moods.indexOf(emotion);
                        moods.get(idx).setMessage(text);
                        moods.get(idx).setDate(new Date());
                        FileEditor.saveInFile(context, filename, moods);
                    } catch (SubscriptionTooLongException e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:

                    try {
                        idx = moods.indexOf(emotion);
                        moods.get(idx).setMessage(text);
                        moods.get(idx).setDate(new Date());
                        FileEditor.saveInFile(context, filename, moods);
                    } catch (SubscriptionTooLongException e) {
                        e.printStackTrace();
                    }
                    break;

                case 4:

                    try {
                        idx = moods.indexOf(emotion);
                        moods.get(idx).setMessage(text);
                        moods.get(idx).setDate(new Date());

                        FileEditor.saveInFile(context, filename, moods);
                    } catch (SubscriptionTooLongException e) {
                        e.printStackTrace();
                    }
                    break;

                case 3:

                    try {
                        idx = moods.indexOf(emotion);
                        moods.get(idx).setMessage(text);
                        moods.get(idx).setDate(new Date());

                        // adapter.notifyDataSetChanged();
                        FileEditor.saveInFile(context, filename, moods);
                    } catch (SubscriptionTooLongException e) {
                        e.printStackTrace();
                    }
                    break;

                case 5:

                    try {
                        idx = moods.indexOf(emotion);
                        moods.get(idx).setMessage(text);
                        moods.get(idx).setDate(new Date());

                        // adapter.notifyDataSetChanged();
                        FileEditor.saveInFile(context, filename, moods);
                    } catch (SubscriptionTooLongException e) {
                        e.printStackTrace();
                    }
                    break;

                case 6:

                    try {
                        idx = moods.indexOf(emotion);
                        moods.get(idx).setMessage(text);
                        moods.get(idx).setDate(new Date());

                        // adapter.notifyDataSetChanged();
                        FileEditor.saveInFile(context, filename, moods);
                    } catch (SubscriptionTooLongException e) {
                        e.printStackTrace();
                    }
                    break;


            }
            finish();
        }
    }

}