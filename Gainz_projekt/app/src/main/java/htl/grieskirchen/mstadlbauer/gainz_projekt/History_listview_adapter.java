package htl.grieskirchen.mstadlbauer.gainz_projekt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class History_listview_adapter extends BaseAdapter {

    /**
     * Normaler Listview adapter
     */
    private List<Workout> histroy = new ArrayList<>();
    private LayoutInflater inflater;
    private int layoutId;

    public History_listview_adapter(Context context, int layoutId, List<Workout> histroy){
        this.histroy = histroy;
        this.layoutId = layoutId;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return histroy.size();
    }

    @Override
    public Object getItem(int position) {
        return histroy.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Setzt Name und das Letzte datum in die ListView
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Workout workout = histroy.get(position);
        View listitem = (convertView==null) ? inflater.inflate(this.layoutId, null) : convertView;
        ((TextView) listitem.findViewById(R.id.history_listView_Name)).setText(workout.getName());
        ((TextView) listitem.findViewById(R.id.histroy_listView_Datum)).setText(workout.getLasttimedate());
        return listitem;
    }
}
