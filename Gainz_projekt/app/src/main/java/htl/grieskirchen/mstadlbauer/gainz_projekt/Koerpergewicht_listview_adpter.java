package htl.grieskirchen.mstadlbauer.gainz_projekt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Koerpergewicht_listview_adpter extends BaseAdapter {
    private List<Körperdaten> arrayList = new ArrayList<>();
    private LayoutInflater inflater;
    private int layoutId;

    public Koerpergewicht_listview_adpter(Context context, int layoutId, List<Körperdaten> arrayList){
        this.arrayList = arrayList;
        this.layoutId = layoutId;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
      return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
     return  arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Körperdaten koerperdaten = arrayList.get(position);
        View listitem = (convertView==null) ? inflater.inflate(this.layoutId, null) : convertView;
        ((TextView) listitem.findViewById(R.id.koerpergewicht_listview_adapter)).setText(String.valueOf(koerperdaten.getGewicht()) + " kg");
        ((TextView) listitem.findViewById(R.id.datumkoerpergewicht)).setText(koerperdaten.getLocalDate().toString());
        return listitem;
    }
}
