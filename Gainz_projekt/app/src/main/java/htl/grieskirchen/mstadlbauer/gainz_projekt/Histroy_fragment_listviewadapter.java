package htl.grieskirchen.mstadlbauer.gainz_projekt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Histroy_fragment_listviewadapter extends BaseAdapter {

    private List<Workout> workoutList = new ArrayList<>();
    private LayoutInflater inflater;
    private int layoutId;

    public Histroy_fragment_listviewadapter(Context context, int layoutId, List<Workout> workoutList){
        this.workoutList = workoutList;
        this.layoutId = layoutId;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return workoutList.size();
    }

    @Override
    public Object getItem(int position) {
        return workoutList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Workout workout = workoutList.get(position);
        View listitem = (convertView==null) ? inflater.inflate(this.layoutId, null) : convertView;
        ((TextView) listitem.findViewById(R.id.daten_workout_name)).setText(workout.getName());
        if(workout.getLasttimedate().equals("null"))
        {
            ((TextView) listitem.findViewById(R.id.daten_workout_date)).setText("Noch nie gemacht");
        }
        else
        {
            ((TextView) listitem.findViewById(R.id.daten_workout_date)).setText(workout.getLasttimedate());
        }
        return listitem;
    }
}
