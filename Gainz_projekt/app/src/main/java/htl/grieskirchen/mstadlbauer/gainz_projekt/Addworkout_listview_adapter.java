package htl.grieskirchen.mstadlbauer.gainz_projekt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Addworkout_listview_adapter extends BaseAdapter {
    /**
     * normaler Adapter
     */
    private List<Uebungen> uebung = new ArrayList<>();
    private LayoutInflater inflater;
    private int layoutId;

    public Addworkout_listview_adapter(Context context, int layoutId, List<Uebungen> uebung) {
        this.uebung = uebung;
        this.layoutId = layoutId;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return uebung.size();
    }

    @Override
    public Object getItem(int position) {
        return uebung.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Setzt name, saetze und wiederholungen
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Uebungen uebungview = uebung.get(position);
        View listitem = (convertView == null) ? inflater.inflate(this.layoutId, null) : convertView;
        ((TextView) listitem.findViewById(R.id.addWorkout_listview_adapter_name)).setText(uebungview.getName());
        ((TextView) listitem.findViewById(R.id.addWorkout_listview_adapter_saetze)).setText(String.valueOf(uebungview.getSaetze()));
        ((TextView) listitem.findViewById(R.id.addWorkout_listview_adapter_wh)).setText(String.valueOf(uebungview.getWh()));
        return listitem;
    }
}
