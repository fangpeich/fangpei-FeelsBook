package ca.fangpei.cs.feelsbook;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import ca.fangpei.cs.feelsbook.Emotions.Mood;

/*
* MoodAdapter method is a assit method for helping deserilizing ArrayList<Mood> obj
* */

public class MoodAdapter implements  JsonDeserializer<Mood>
{

    @Override
    public Mood deserialize (JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException{
        JsonObject jsonObject = json.getAsJsonObject();
        String id = jsonObject.get("emotion_id").getAsString();
        String type = null;
        //JsonElement element = jsonObject.
        switch (Integer.parseInt(id)) {
            case 1:
                type = "Joy";
                break;
            case 2:
                type = "Love";
                break;
            case 3:
                type = "Surprise";
                break;
            case 4 :
                type = "Anger";
                break;
            case 5:
                type = "Sadness";
                break;
            case 6:
                type = "Fear";
                break;

        }

        try {

            return context.deserialize(jsonObject, Class.forName("ca.fangpei.cs.feelsbook.Emotions." + type));

        } catch (ClassNotFoundException cnfe) {

            throw new JsonParseException("Unknown element type: " + type, cnfe);

        }

    }

}


