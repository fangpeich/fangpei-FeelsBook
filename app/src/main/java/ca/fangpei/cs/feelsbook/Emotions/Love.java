package ca.fangpei.cs.feelsbook.Emotions;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;

public class Love extends Mood  implements Serializable {
    final static String id = "LOVE.PREFERENCE_FILE_KEY";
    //String record;

    public Love(Context context){
        this.setName("LOVE");
        this.setEmotion_id(2);
        SharedPreferences srp = context.getSharedPreferences(id,0);

        this.setCount(srp.getInt("love_count", -1) );

        if (this.getCount() == -1)
        {

            SharedPreferences.Editor editor = srp.edit();
            editor.putInt("love_count", 0);
            editor.apply();
        }
        else{}
    }


    public static void clear(Context context, String id)
    {
        SharedPreferences srp = context.getSharedPreferences(id,0);
        SharedPreferences.Editor editor = srp.edit();
        editor.putInt("love_count", 0);
        editor.apply();
    }

    public void addCount(Context context)
    {

        SharedPreferences srp = context.getSharedPreferences(id,0);
        this.setCount(srp.getInt("love_count",0));
        this.setCount(this.getCount()+1);
        SharedPreferences.Editor editor = srp.edit();
        editor.putInt("love_count", this.getCount());
        editor.apply();
    }

    public void subCount(Context context)
    {

        SharedPreferences srp = context.getSharedPreferences(id,0);
        this.setCount(srp.getInt("love_count",0));
        this.setCount(this.getCount()-1);
        SharedPreferences.Editor editor = srp.edit();
        editor.putInt("love_count", this.getCount());
        editor.apply();
    }


    public int getLove_count(Context context, String id)
    {
        SharedPreferences srp = context.getSharedPreferences(id,0);
        setCount(srp.getInt("love_count",0));
        return getCount();
    }




}
