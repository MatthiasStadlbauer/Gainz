package htl.grieskirchen.mstadlbauer.gainz_projekt;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.util.ArrayList;


public class Daten_fragment extends Fragment {

    private FloatingActionButton floatingActionButton;
    private ArrayList<Körperdaten> körpergewicht = new ArrayList<>();
    private ListView lv;
    private Koerpergewicht_listview_adpter kla;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_daten_fragment, container, false);

        initButtons(view);
        initViews(view);
        return view;
    }

    private void initButtons(View view){
        floatingActionButton = view.findViewById(R.id.floatingactionbutton_daten);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showDialog();
            }
        });


        }


        private void initViews(View v){
            lv = v.findViewById(R.id.datenfragment_listivew);
            kla = new Koerpergewicht_listview_adpter(getContext(), R.layout.koerpergewicht_listview_adapter, körpergewicht);
            lv.setAdapter(kla);
            registerForContextMenu(lv);
        }

        private void showDialog(){
            System.out.println("Test");
            final View vDialog = getLayoutInflater().inflate(R.layout.koerpergewicht_hinzufuegen, null);
            new AlertDialog.Builder(getContext()).setMessage("Derzeitiges Gewicht").setCancelable(false).setView(vDialog).setPositiveButton("Add", ((dialog, which) -> handleDialog(vDialog))).setNegativeButton("Cancle", null).show();
        }

        private void handleDialog(final View vDialog){
            EditText koerpergewicht = vDialog.findViewById(R.id.koerpergewicht);
            String gewicht = koerpergewicht.getText().toString();
            körpergewicht.add(new Körperdaten(Double.parseDouble(gewicht), LocalDate.now()));
            kla.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        int viewid = v.getId();
        if(viewid == R.id.datenfragment_listivew){
            getActivity().getMenuInflater().inflate(R.menu.daten_fragment_kontextmenue, menu);
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Körperdaten koerperdaten = null;
        if (info != null) {
            long id = info.id;
            int pos = info.position;
            koerperdaten = info != null ? (Körperdaten) lv.getAdapter().getItem(pos) : null;
            if (item.getItemId() == R.id.daten_fragment_kontextmenue_delete) {
                körpergewicht.remove(koerperdaten);
                kla.notifyDataSetChanged();
            }
        }
        return super.onContextItemSelected(item);
    }
}