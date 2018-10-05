package ca.fangpei.cs.feelsbook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ca.fangpei.cs.feelsbook.Emotions.Mood;

/**
 * Created by User on 3/14/2017.
 */

public class PersonListAdapter extends ArrayAdapter<Mood> {

    private static final String TAG = "PersonListAdapter";

    private static Date date;


    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView name;
        TextView date;
        TextView msg;
    }

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public PersonListAdapter(Context context, int resource, ArrayList<Mood> objects) {
        super(context, resource, objects);
        //Log.i("cfp","construcotr");
        mContext = context;
        mResource = resource;

    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //get the persons information
        String name = getItem(position).getName();
        date = getItem(position).getDate();
        String msg = getItem(position).getMessage();




        //create the view result for showing the animation
        final View result;

        //ViewHolder object
        ViewHolder holder;


        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.textView1);
            holder.date = (TextView) convertView.findViewById(R.id.textView2);
            holder.msg = (TextView) convertView.findViewById(R.id.textView3);

            result = convertView;

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
            //result = convertView;
        }

       holder.date.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


            }
        });

        lastPosition = position;

        DateFormat pattern = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");


        holder.name.setText(name);
        holder.date.setText(pattern.format(date));
        holder.msg.setText(msg);
        //Log.i("fangpei", date.toString());

        return convertView;
    }


}

























