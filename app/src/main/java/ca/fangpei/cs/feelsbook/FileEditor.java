package ca.fangpei.cs.feelsbook;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import ca.fangpei.cs.feelsbook.Emotions.Mood;
/*
* Class FileEditor is responsible for operations related to load/save data to file.
*
* */
public class FileEditor {

    /*
    * loadFromFIle method return a mood arraylist by desirializing json data
    * */
    public static ArrayList<Mood> loadFromFile(Context context, String FILENAME, ArrayList<Mood> moods) {

        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);

            GsonBuilder gson = new GsonBuilder();
            gson.registerTypeAdapter(Mood .class, new MoodAdapter());
            //Gson gson = new Gson();
            //Type listTweetTYpe = new TypeToken<ArrayList<Tweet>>();
            //!!!!the code below may need a subclass like joy, Arraylist<joy>
            Type moodType = new TypeToken<ArrayList<Mood>>(){}.getType();
            //Log.i("fangpei","success");
            moods = gson.create().fromJson(reader, moodType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            moods = new ArrayList<Mood>();

            e.printStackTrace();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return moods;
    }

    /*
    * method saveInfile store mood arraylist into file by serializing it
    * */

    public static void saveInFile(Context context, String FILENAME, ArrayList<Mood> moods) {
        try {

            FileOutputStream fos = context.openFileOutput(FILENAME,0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter writer = new BufferedWriter(osw);
            Gson gson = new Gson();

            gson.toJson(moods, writer);

            writer.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}
