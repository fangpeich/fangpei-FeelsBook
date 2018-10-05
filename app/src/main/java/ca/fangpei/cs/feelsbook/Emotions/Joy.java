package ca.fangpei.cs.feelsbook.Emotions;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;

public class Joy extends Mood  implements Serializable {
    final String id = "JOY.PREFERENCE_FILE_KEY";
    //String record;

    public Joy(Context context){
        this.setName("JOY");
        this.setEmotion_id(1);
        SharedPreferences srp = context.getSharedPreferences(id,0);

        this.setCount(srp.getInt("joy_count", -1) );

        if (this.getCount() == -1)
        {

            SharedPreferences.Editor editor = srp.edit();
            editor.putInt("joy_count", 0);
            editor.apply();
        }
        else{}
    }


    public static void clear(Context context, String id)
    {
        SharedPreferences srp = context.getSharedPreferences(id,0);
        SharedPreferences.Editor editor = srp.edit();
        editor.putInt("joy_count", 0);
        editor.apply();
    }

    public void addCount(Context context)
    {

        SharedPreferences srp = context.getSharedPreferences(id,0);
        this.setCount(srp.getInt("joy_count",0));
        this.setCount(this.getCount()+1);
        SharedPreferences.Editor editor = srp.edit();
        editor.putInt("joy_count", this.getCount());
        editor.apply();
    }

    public void subCount(Context context)
    {

        SharedPreferences srp = context.getSharedPreferences(id,0);
        this.setCount(srp.getInt("joy_count",0));
        this.setCount(this.getCount()-1);
        SharedPreferences.Editor editor = srp.edit();
        editor.putInt("joy_count", this.getCount());
        editor.apply();
    }


    public int getJoyCount(Context context, String id)
    {
        SharedPreferences srp = context.getSharedPreferences(id,0);
        setCount(srp.getInt("joy_count",0));
        return getCount();
    }




}
