package htl.grieskirchen.mstadlbauer.gainz_projekt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class History_fragment extends Fragment {

    /**
     * historylistview = Listview mit Name und Datum des Workouts
     * adapter = listviewadapter
     * history = sammlung der Workouts
     */
    private ListView historylistview;
    private History_listview_adapter adapter;
    private List<Workout> history = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history_fragment, container, false);
        // Inflate the layout for this fragment
        init(view);
        return view;
    }

    /**
     * Initialisierung der listview und des adapters
     * @param view
     */
    private void init(View view)
    {
        historylistview = view.findViewById(R.id.history_fragment_ListView);
        adapter = new History_listview_adapter(view.getContext(), R.layout.history_listview_adapter, history);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}