package ca.fangpei.cs.feelsbook.Emotions;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;

public class Anger extends Mood  implements Serializable {
    final String id = "ANGER.PREFERENCE_FILE_KEY";
    private int anger_count;
    //String record;

    public Anger(Context context){
        this.setName("ANGER");
        this.setEmotion_id(4);
        SharedPreferences srp = context.getSharedPreferences(id,0);

        anger_count = srp.getInt("anger_count", -1);

        if (anger_count == -1)
        {

            SharedPreferences.Editor editor = srp.edit();
            editor.putInt("anger_count", 0);
            editor.apply();
        }
        else{}
    }


    public static void clear(Context context, String id)
    {
        SharedPreferences srp = context.getSharedPreferences(id,0);
        SharedPreferences.Editor editor = srp.edit();
        editor.putInt("anger_count", 0);
        editor.apply();
    }

    public void addCount(Context context)
    {

        SharedPreferences srp = context.getSharedPreferences(id,0);
        anger_count = srp.getInt("anger_count",0);
        anger_count++;
        SharedPreferences.Editor editor = srp.edit();
        editor.putInt("anger_count", anger_count);
        editor.apply();
    }

    public void subCount(Context context)
    {

        SharedPreferences srp = context.getSharedPreferences(id,0);
        anger_count = srp.getInt("anger_count",0);
        anger_count--;
        SharedPreferences.Editor editor = srp.edit();
        editor.putInt("anger_count", anger_count);
        editor.apply();
    }


    public int getAnger_count(Context context, String id)
    {
        SharedPreferences srp = context.getSharedPreferences(id,0);
        anger_count = srp.getInt("anger_count",0);
        return anger_count;
    }


}
