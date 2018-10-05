package ca.fangpei.cs.feelsbook.Emotions;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;

public class Sadness extends Mood  implements Serializable {
    final String id = "SADNESS.PREFERENCE_FILE_KEY";
    private int sadness_count;
    //String record;

    public Sadness(Context context){
        this.setName("SADNESS");
        this.setEmotion_id(5);
        SharedPreferences srp = context.getSharedPreferences(id,0);

        sadness_count = srp.getInt("sadness_count", -1);

        if (sadness_count == -1)
        {

            SharedPreferences.Editor editor = srp.edit();
            editor.putInt("sadness_count", 0);
            editor.apply();
        }
        else{}
    }

    public static void clear(Context context, String id)
    {
        SharedPreferences srp = context.getSharedPreferences(id,0);
        SharedPreferences.Editor editor = srp.edit();
        editor.putInt("sadness_count", 0);
        editor.apply();
    }

    public void addCount(Context context)
    {
        SharedPreferences srp = context.getSharedPreferences(id,0);
        sadness_count = srp.getInt("sadness_count",0);
        sadness_count++;
        SharedPreferences.Editor editor = srp.edit();
        editor.putInt("sadness_count", sadness_count);
        editor.apply();
    }

    public void subCount(Context context)
    {
        SharedPreferences srp = context.getSharedPreferences(id,0);
        sadness_count = srp.getInt("sadness_count",0);
        sadness_count--;
        SharedPreferences.Editor editor = srp.edit();
        editor.putInt("sadness_count", sadness_count);
        editor.apply();
    }

    public int getSadness_count(Context context, String id)
    {
        SharedPreferences srp = context.getSharedPreferences(id,0);
        sadness_count = srp.getInt("sadness_count",0);
        return sadness_count;
    }


}
