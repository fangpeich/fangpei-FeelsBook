package ca.fangpei.cs.feelsbook.Emotions;
/*
* This is the parent method of all emotions
*
* */

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
    private int count;
    String record;




    public String getMessage() {
        return this.message;
    }

    public void setDate (Date date){
        this.date = date;
    }

    public void setName(String name) {this.name=name;}

    public Date getDate() { return this.date; }

    /*
    * ToString method output formated string
    * */

    public String toString()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

       record =  dateFormat.format(getDate())
                + " " + getName()+" "+getMessage();
        return record;
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

    /*
    * rewrite compareTo metho to compare two Mood obj
    *
    * */

    @Override
    public int compareTo(@NonNull Mood m) {


        return (getDate().compareTo(m.getDate()) );
    }
/*
* override equals method in order to get the correct
* index of a obj
*
* */
    @Override
    public boolean equals(Object obj)
    {
        if (obj == null){
            return false;
        }
        if (obj instanceof Mood)
        {
            Mood m = (Mood)obj;
            if(m.count==count && m.emotion_id==emotion_id)
            {
                return true;
            }
        }
        return false;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
