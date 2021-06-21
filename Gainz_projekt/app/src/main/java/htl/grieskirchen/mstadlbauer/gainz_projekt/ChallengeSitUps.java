package htl.grieskirchen.mstadlbauer.gainz_projekt;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.content.Context.MODE_APPEND;
import static android.content.Context.MODE_PRIVATE;
import static android.os.Build.VERSION.SDK_INT;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChallengeSitUps#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChallengeSitUps extends Fragment {

    Button buttonRemoveSitUpsAnfänger;
    Button buttonAddSitUpsAnfänger;
    Button buttonFinalAddSitUpsAnfänger;

    Button buttonRemoveSitUpsFortgeschritten;
    Button buttonAddSitUpsFortgeschritten;
    Button buttonFinalSitUpsFortgeschritten;

    TextView doneSitUpsAnfänger;
    EditText addSitUpsAnfänger;

    TextView doneSitUpsFortgeschritten;
    EditText addSitUpsFortgeschritten;

    String fileName = "ChallangeSitUpsFile";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChallengeSitUps() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChallengeSitUps.
     */
    // TODO: Rename and change types and number of parameters
    public static ChallengeSitUps newInstance(String param1, String param2) {
        ChallengeSitUps fragment = new ChallengeSitUps();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private void initButtonsAnfänger(View view) {
        buttonRemoveSitUpsAnfänger = view.findViewById(R.id.button_remove_sit_ups_anfänger);
        buttonRemoveSitUpsAnfänger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sitUps = addSitUpsAnfänger.getText().toString();
                Integer intSitUps = Integer.parseInt(sitUps) - 1;
                addSitUpsAnfänger.setText(intSitUps.toString());
            }
        });

        buttonAddSitUpsAnfänger = view.findViewById(R.id.button_add_sit_ups_anfänger);
        buttonAddSitUpsAnfänger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sitUps = addSitUpsAnfänger.getText().toString();
                Integer intSitUps = Integer.parseInt(sitUps) + 1;
                addSitUpsAnfänger.setText(intSitUps.toString());
            }
        });

        buttonFinalAddSitUpsAnfänger = view.findViewById(R.id.final_button_add_sit_ups_anfänger);
        buttonFinalAddSitUpsAnfänger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sitUpsDone = doneSitUpsAnfänger.getText().toString();
                String addSitUps = addSitUpsAnfänger.getText().toString();
                Integer intSitUpsDone = Integer.parseInt(sitUpsDone) + Integer.parseInt(addSitUps);
                doneSitUpsAnfänger.setText(intSitUpsDone.toString());
            }
        });
    }

    private void initButtonsFortgeschritten(View view) {
        buttonRemoveSitUpsFortgeschritten = view.findViewById(R.id.button_remove_sit_ups_fortgeschritten);
        buttonRemoveSitUpsFortgeschritten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sitUps = addSitUpsFortgeschritten.getText().toString();
                Integer intSitUps = Integer.parseInt(sitUps) - 1;
                addSitUpsFortgeschritten.setText(intSitUps.toString());
            }
        });

        buttonAddSitUpsFortgeschritten = view.findViewById(R.id.button_add_sit_ups_fortgeschritten);
        buttonAddSitUpsFortgeschritten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sitUps = addSitUpsFortgeschritten.getText().toString();
                Integer intSitUps = Integer.parseInt(sitUps) + 1;
                addSitUpsFortgeschritten.setText(intSitUps.toString());
            }
        });

        buttonFinalSitUpsFortgeschritten = view.findViewById(R.id.final_button_add_sit_ups_fortgeschritten);
        buttonFinalSitUpsFortgeschritten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sitUpsDone = doneSitUpsFortgeschritten.getText().toString();
                String addSitUps = addSitUpsFortgeschritten.getText().toString();
                Integer intSitUpsDone = Integer.parseInt(sitUpsDone) + Integer.parseInt(addSitUps);
                doneSitUpsFortgeschritten.setText(intSitUpsDone.toString());
            }
        });
    }

    private void initViews(View view) {
        doneSitUpsAnfänger = view.findViewById(R.id.done_sit_ups_anfänger);
        addSitUpsAnfänger = view.findViewById(R.id.add_sit_ups_anfänger);

        doneSitUpsFortgeschritten = view.findViewById(R.id.done_sit_ups_fortgeschritten);
        addSitUpsFortgeschritten = view.findViewById(R.id.add_sit_ups_fortgeschritten);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_challenge_sit_ups, container, false);

        initButtonsAnfänger(view);
        initButtonsFortgeschritten(view);

        initViews(view);

        if(!checkPermission()) {
            requestPermission();
        }

        load();

        return view;
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
            out.print(doneSitUpsAnfänger.getText().toString() + ";" + doneSitUpsFortgeschritten.getText().toString());
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
            String[] teile = temp.split(";");
            doneSitUpsAnfänger.setText(teile[0]);
            doneSitUpsFortgeschritten.setText(teile[1]);

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