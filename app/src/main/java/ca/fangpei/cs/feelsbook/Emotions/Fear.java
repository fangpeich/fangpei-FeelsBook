package ca.fangpei.cs.feelsbook.Emotions;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;

public class Fear extends Mood  implements Serializable {
    final String id = "FEAR.PREFERENCE_FILE_KEY";
    private int fear_count;
    //String record;

    public Fear(Context context){
        this.setName("FEAR");
        this.setEmotion_id(6);
        SharedPreferences srp = context.getSharedPreferences(id,0);

        fear_count = srp.getInt("fear_count", -1);

        if (fear_count == -1)
        {

            SharedPreferences.Editor editor = srp.edit();
            editor.putInt("fear_count", 0);
            editor.apply();
        }
        else{}
    }

    public static void clear(Context context, String id)
    {
        SharedPreferences srp = context.getSharedPreferences(id,0);
        SharedPreferences.Editor editor = srp.edit();
        editor.putInt("fear_count", 0);
        editor.apply();
    }

    public void addCount(Context context)
    {

        SharedPreferences srp = context.getSharedPreferences(id,0);
        fear_count = srp.getInt("fear_count",0);
        fear_count++;
        SharedPreferences.Editor editor = srp.edit();
        editor.putInt("fear_count", fear_count);
        editor.apply();
    }

    public void subCount(Context context)
    {

        SharedPreferences srp = context.getSharedPreferences(id,0);
        fear_count = srp.getInt("fear_count",0);
        fear_count--;
        SharedPreferences.Editor editor = srp.edit();
        editor.putInt("fear_count", fear_count);
        editor.apply();
    }

    public int getFear_count(Context context, String id)
    {
        SharedPreferences srp = context.getSharedPreferences(id,0);
        fear_count = srp.getInt("fear_count",0);
        return fear_count;
    }


}
