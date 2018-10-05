package ca.fangpei.cs.feelsbook.Emotions;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;

public class Fear extends Mood  implements Serializable {
    final String id = "FEAR.PREFERENCE_FILE_KEY";
    //String record;

    public Fear(Context context){
        this.setName("FEAR");
        this.setEmotion_id(6);
        SharedPreferences srp = context.getSharedPreferences(id,0);

        this.setCount(srp.getInt("fear_count", -1) );

        if (this.getCount() == -1)
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
        this.setCount(srp.getInt("fear_count",0));
        this.setCount(this.getCount()+1);
        SharedPreferences.Editor editor = srp.edit();
        editor.putInt("fear_count", this.getCount());
        editor.apply();
    }

    public void subCount(Context context)
    {

        SharedPreferences srp = context.getSharedPreferences(id,0);
        this.setCount(srp.getInt("fear_count",0));
        this.setCount(this.getCount()-1);
        SharedPreferences.Editor editor = srp.edit();
        editor.putInt("fear_count", this.getCount());
        editor.apply();
    }


    public int getFear_count(Context context, String id)
    {
        SharedPreferences srp = context.getSharedPreferences(id,0);
        setCount(srp.getInt("fear_count",0));
        return getCount();
    }



}
