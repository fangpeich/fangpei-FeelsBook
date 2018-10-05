package ca.fangpei.cs.feelsbook.Emotions;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import ca.fangpei.cs.feelsbook.SubscriptionTooLongException;

public abstract class Mood implements Serializable, Comparable<Mood> {

    private Date date;
    private String message;
    private String name;
    private int emotion_id;
    String record;




    public String getMessage() {
        return this.message;
    }

    public void setDate (Date date){
        this.date = date;
    }

    public void setName(String name) {this.name=name;}

    public Date getDate() { return this.date; }

    public String getFormatDate()
    {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return dateFormat.format(this.date);
    }

    public String toString()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

       record =  dateFormat.format(getDate())
                + " " + getName()+" "+getMessage();
        return record;
       //return this.date.toString()+"|"+message;
    }


    public void setMessage(String message) throws SubscriptionTooLongException {

            this.message = message;
    }

    public String getName() {
        return name;
    }

    public int getEmotion_id() {
        return emotion_id;
    }

    public void setEmotion_id(int emotion_id) {
        this.emotion_id = emotion_id;
    }

    @Override
    public int compareTo(@NonNull Mood m) {


        return (getDate().compareTo(m.getDate()) );
    }
}
