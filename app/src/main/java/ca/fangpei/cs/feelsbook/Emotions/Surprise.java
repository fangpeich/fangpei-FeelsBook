package ca.fangpei.cs.feelsbook.Emotions;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;

public class Surprise extends Mood  implements Serializable {
    final String id = "SURPRISE.PREFERENCE_FILE_KEY";
    private int surprise_count;
    //String record;

    public Surprise(Context context){
        this.setName("SURPRISE");
        this.setEmotion_id(3);
        SharedPreferences srp = context.getSharedPreferences(id,0);

        surprise_count = srp.getInt("surprise_count", -1);

        if (surprise_count == -1)
        {

            SharedPreferences.Editor editor = srp.edit();
            editor.putInt("surprise_count", 0);
            editor.apply();
        }
        else{}
    }

    public static void clear(Context context, String id)
    {
        SharedPreferences srp = context.getSharedPreferences(id,0);
        SharedPreferences.Editor editor = srp.edit();
        editor.putInt("surprise_count", 0);
        editor.apply();
    }

    public void addCount(Context context)
    {

        SharedPreferences srp = context.getSharedPreferences(id,0);
        surprise_count = srp.getInt("surprise_count",0);
        surprise_count++;
        SharedPreferences.Editor editor = srp.edit();
        editor.putInt("surprise_count", surprise_count);
        editor.apply();
    }
    public void subCount(Context context)
    {

        SharedPreferences srp = context.getSharedPreferences(id,0);
        surprise_count = srp.getInt("surprise_count",0);
        surprise_count--;
        SharedPreferences.Editor editor = srp.edit();
        editor.putInt("surprise_count", surprise_count);
        editor.apply();
    }
    public int getSurprise_count(Context context, String id)
    {
        SharedPreferences srp = context.getSharedPreferences(id,0);
        surprise_count = srp.getInt("surprise_count",0);
        return surprise_count;
    }


}
