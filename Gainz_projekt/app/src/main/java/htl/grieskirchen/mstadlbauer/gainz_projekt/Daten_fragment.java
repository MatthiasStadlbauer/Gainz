package htl.grieskirchen.mstadlbauer.gainz_projekt;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.Settings;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;


public class Daten_fragment extends Fragment {

    private FloatingActionButton floatingActionButton;
    private ArrayList<Körperdaten> körpergewicht = new ArrayList<>();
    private ListView lv;
    private Koerpergewicht_listview_adpter kla;
    private EditText zielGewicht;

    String fileName = "KoerperdatenFile";

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
            zielGewicht = v.findViewById(R.id.gewicht_ziel);
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

    private boolean checkPermission() {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager();
        } else {
            int result = ContextCompat.checkSelfPermission(getContext(), READ_EXTERNAL_STORAGE);
            int result1 = ContextCompat.checkSelfPermission(getContext(), WRITE_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
        }
    }

    private void requestPermission() {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            try {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(String.format("package:%s", new Object[]{getActivity().getApplicationContext().getPackageName()})));
                startActivityForResult(intent, 2296);
            } catch (Exception e) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivityForResult(intent, 2296);
            }
        } else {
            //below android 11
            ActivityCompat.requestPermissions(getActivity(), new String[]{WRITE_EXTERNAL_STORAGE}, 187);
        }
    }

    private void save() {
        String state = Environment.getExternalStorageState();
        if (!state.equals(Environment.MEDIA_MOUNTED)) return;
        File outFile = Environment.getExternalStorageDirectory();
        String path = outFile.getAbsolutePath();
        String fullPath = path + File.separator + fileName;
        try {
            PrintWriter out = new PrintWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(fullPath)));
            out.print(zielGewicht.getText().toString() + ":");
            for (Körperdaten körperdaten : körpergewicht) {
                out.print(körperdaten.toString() + ":");
            }
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void load() {
        String state = Environment.getExternalStorageState();
        if (!state.equals(Environment.MEDIA_MOUNTED)) return;
        File outFile = Environment.getExternalStorageDirectory();
        String path = outFile.getAbsolutePath();
        String fullPath = path + File.separator + fileName;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fullPath)));

            String line;
            StringBuffer buffer = new StringBuffer();

            while((line = in.readLine()) != null) {
                buffer.append(line);
            }

            in.close();

            String temp = buffer.toString();
            String[] teile = temp.split(":");
            zielGewicht.setText(teile[0]);

            for (int i = 1; i < teile.length; i++) {
                String[] s = teile[i].split(";");
                Körperdaten k = new Körperdaten(Double.parseDouble(s[0]), LocalDate.parse(s[1]));
                körpergewicht.add(k);
                kla.notifyDataSetChanged();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        load();
    }

    @Override
    public void onPause() {
        super.onPause();
        save();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        save();
    }
}