package ca.fangpei.cs.feelsbook;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import ca.fangpei.cs.feelsbook.Emotions.Anger;
import ca.fangpei.cs.feelsbook.Emotions.Fear;
import ca.fangpei.cs.feelsbook.Emotions.Joy;
import ca.fangpei.cs.feelsbook.Emotions.Love;
import ca.fangpei.cs.feelsbook.Emotions.Mood;
import ca.fangpei.cs.feelsbook.Emotions.Sadness;
import ca.fangpei.cs.feelsbook.Emotions.Surprise;

public class HistoryOperation extends Activity
    /*
    view history
    edit history (edit, or delete a history)
     */
{

    private ListView emotionList;
    private PersonListAdapter adapter ;
    private Calendar cal;
    private int year ;
    private int month ;
    private int day ;
    private int hour ;
    private int min ;
    private int finalPosition;
    private ArrayList<Mood> emotions;





    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_view);

        emotions =  FileEditor.loadFromFile(HistoryOperation.this,FeelsBook.FILENAME,emotions);

        //Button clearButton = (Button) findViewById(R.id.clear);
        Button backButton = (Button) findViewById(R.id.back);
        emotionList = (ListView) findViewById(R.id.oldEmotionList);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        emotionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(final AdapterView<?> adapterView, View view, final int position, long l) {

                AlertDialog.Builder custom = new AlertDialog.Builder(HistoryOperation.this);;
                custom.setCancelable(true);




                finalPosition = position;

               custom.setPositiveButton("Edit Date", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogInterface, int i) {
                        cal = Calendar.getInstance();
                        Date date = emotions.get(finalPosition).getDate();

                        cal.setTime(date);
                        //Log.i("cfp", cal.getTime().toString());
                        year = cal.get(Calendar.YEAR);
                        month =cal.get(Calendar.MONTH);
                        day = cal.get(Calendar.DAY_OF_MONTH);
                        hour = cal.get(Calendar.HOUR_OF_DAY);
                        min =cal.get(Calendar.MINUTE);
                        // emotion used to remember which item are being edited
                        final Mood emotion = emotions.get(finalPosition);

                        //Log.i("cfp", String.valueOf(year));


                        new TimePickerDialog(HistoryOperation.this,
                                AlertDialog.THEME_HOLO_LIGHT, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                cal.setTime(emotions.get(finalPosition).getDate());
                                year = cal.get(Calendar.YEAR);
                                month =cal.get(Calendar.MONTH);
                                day = cal.get(Calendar.DAY_OF_MONTH);
                                setTime(position,year,month,day,i,i1,cal,emotions, adapter);
                                FileEditor.saveInFile(HistoryOperation.this,FeelsBook.FILENAME,emotions);
                            }
                        }   ,hour
                                ,min
                                ,true).show();


                        new DatePickerDialog(HistoryOperation.this,
                                AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                                setDate(finalPosition,i,i1,i2,cal,emotions,adapter);
                                FileEditor.saveInFile(HistoryOperation.this,FeelsBook.FILENAME,emotions);

                                //get the postion of the object which are sorted in the list

                                finalPosition = emotions.indexOf(emotion);
                            }
                        }   ,year
                            ,month
                            ,day).show();



                    }
                });


                custom.setNegativeButton("Edit Message", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {

                                AlertDialog.Builder msg_editor = new AlertDialog.Builder(HistoryOperation.this);
                                //LayoutInflater inflater = FeelsBookActivity.this.getLayoutInflater();
                                final EditText user_input = new EditText(HistoryOperation.this);
                                msg_editor.setView(user_input);
                                msg_editor.setCancelable(true);


                                msg_editor.setPositiveButton("Finish", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogInterface, int i) {


                                        final String msg = user_input.getText().toString();

                                        editMsg(msg,finalPosition,emotions);
                                        adapter.notifyDataSetChanged();
                                        FileEditor.saveInFile(HistoryOperation.this, FeelsBook.FILENAME, emotions);

                                        //notifyAll();
                                    }

                                });
                                msg_editor.show();
                            }
                        });

                custom.setNeutralButton("Delete Emotion", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        

                        //identify which emotion the mood is, then reduce its count
                        deleteEmotion(finalPosition,emotions,HistoryOperation.this);

                        adapter.notifyDataSetChanged();
                        //notifyAll();
                    }
                });



                custom.show();
            }
        });


    }

    @Override
    protected void onStart() {
        // calling super class' onStart method
        super.onStart();
        //Log.i("cfp","construcotr");
        emotions = FileEditor.loadFromFile(HistoryOperation.this, FeelsBook.FILENAME, emotions);

        adapter = new PersonListAdapter(this,
                R.layout.adapter_view_layout, emotions) {
        };
        emotionList.setAdapter(adapter);
    }

    private void editMsg(String msg, int position, ArrayList<Mood> moods )
    {

        try {
            moods.get(position).setMessage(msg);
        } catch (SubscriptionTooLongException e) {
            e.printStackTrace();
        }
    }

    private void deleteEmotion (int position, ArrayList<Mood> moods, Context context)
    {
        Mood mood = moods.get(position);

        //identify which emotion the mood is, then reduce its count
        switch (mood.getEmotion_id()) {
            case 1:

                Joy joy = new Joy(context);
                joy.subCount(context);
                break;

            case 2:
                Love love = new Love(context);
                love.subCount(context);
                break;
            case 3:

                Surprise surprise = new Surprise(context);
                surprise.subCount(context);
                break;
            case 4:
                Anger anger = new Anger(context);
                anger.subCount(context);
                ;
                break;
            case 5:

                Sadness sadness = new Sadness(context);
                sadness.subCount(context);
                break;
            case 6:
                Fear fear = new Fear(context);
                fear.subCount(context);
                break;
        }

        moods.remove(mood);
        FileEditor.saveInFile(HistoryOperation.this,FeelsBook.FILENAME,moods);
    }

    private void setTime(int position, int year, int month, int day, int hour, int min, Calendar calendar, ArrayList<Mood> moods, PersonListAdapter adapter)
    {
        calendar.setTime(moods.get(position).getDate());
        calendar.set(year,month,day,hour,min);


        Date date;
        date = calendar.getTime();

        moods.get(position).setDate(date);

        Collections.sort(moods);

        adapter.notifyDataSetChanged();
    }
    private void setDate(int position, int year, int month, int day, Calendar calendar, ArrayList<Mood> moods, PersonListAdapter adapter )
    {

        Date date;
        calendar.set(year,month,day);
        date = calendar.getTime();

        moods.get(position).setDate(date);
        Collections.sort(moods);
        adapter.notifyDataSetChanged();
    }



}