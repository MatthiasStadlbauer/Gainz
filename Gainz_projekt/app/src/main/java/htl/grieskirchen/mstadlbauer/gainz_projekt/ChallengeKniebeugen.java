package htl.grieskirchen.mstadlbauer.gainz_projekt;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChallengeKniebeugen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChallengeKniebeugen extends Fragment {

    Button buttonRemoveKniebeugenAnfänger;
    Button buttonAddKniebeugenAnfänger;
    Button buttonFinalAddKniebeugenAnfänger;

    Button buttonRemoveKniebeugenFortgeschritten;
    Button buttonAddKniebeugenFortgeschritten;
    Button buttonFinalKniebeugenFortgeschritten;

    TextView doneKniebeugenAnfänger;
    EditText addKniegbeugenAnfänger;

    TextView doneKniebeugenFortgeschritten;
    EditText addKniebeugenFortgeschritten;

    String fileName = "ChallengeKniebeugenFile";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChallengeKniebeugen() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChallengeKniebeugen.
     */
    // TODO: Rename and change types and number of parameters
    public static ChallengeKniebeugen newInstance(String param1, String param2) {
        ChallengeKniebeugen fragment = new ChallengeKniebeugen();
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
        buttonRemoveKniebeugenAnfänger = view.findViewById(R.id.button_remove_liegestuetz_anfänger);
        buttonRemoveKniebeugenAnfänger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String liegestuetz = addKniegbeugenAnfänger.getText().toString();
                Integer intLiegestuetz = Integer.parseInt(liegestuetz) - 1;
                addKniegbeugenAnfänger.setText(intLiegestuetz.toString());
            }
        });

        buttonAddKniebeugenAnfänger = view.findViewById(R.id.button_add_liegestuetz_anfänger);
        buttonAddKniebeugenAnfänger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String liegestuetz = addKniegbeugenAnfänger.getText().toString();
                Integer intLiegestuetz = Integer.parseInt(liegestuetz) + 1;
                addKniegbeugenAnfänger.setText(intLiegestuetz.toString());
            }
        });

        buttonFinalAddKniebeugenAnfänger = view.findViewById(R.id.final_button_add_liegestuetz_anfänger);
        buttonFinalAddKniebeugenAnfänger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String liegestuetzDone = doneKniebeugenAnfänger.getText().toString();
                String addLiegestuetz = addKniegbeugenAnfänger.getText().toString();
                Integer intLiegestuetzDone = Integer.parseInt(liegestuetzDone) + Integer.parseInt(addLiegestuetz);
                doneKniebeugenAnfänger.setText(intLiegestuetzDone.toString());
            }
        });
    }

    private void initButtonsFortgeschritten(View view) {
        buttonRemoveKniebeugenFortgeschritten = view.findViewById(R.id.button_remove_liegestuetz_fortgeschritten);
        buttonRemoveKniebeugenFortgeschritten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String liegestuetz = addKniebeugenFortgeschritten.getText().toString();
                Integer intLiegestuetz = Integer.parseInt(liegestuetz) - 1;
                addKniebeugenFortgeschritten.setText(intLiegestuetz.toString());
            }
        });

        buttonAddKniebeugenFortgeschritten = view.findViewById(R.id.button_add_liegestuetz_fortgeschritten);
        buttonAddKniebeugenFortgeschritten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String liegestuetz = addKniebeugenFortgeschritten.getText().toString();
                Integer intLiegestuetz = Integer.parseInt(liegestuetz) + 1;
                addKniebeugenFortgeschritten.setText(intLiegestuetz.toString());
            }
        });

        buttonFinalKniebeugenFortgeschritten = view.findViewById(R.id.final_button_add_liegestuetz_fortgeschritten);
        buttonFinalKniebeugenFortgeschritten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String liegestuetzDone = doneKniebeugenFortgeschritten.getText().toString();
                String addLiegestuetz = addKniebeugenFortgeschritten.getText().toString();
                Integer intLiegestuetzDone = Integer.parseInt(liegestuetzDone) + Integer.parseInt(addLiegestuetz);
                doneKniebeugenFortgeschritten.setText(intLiegestuetzDone.toString());
            }
        });
    }

    private void initViews(View view) {
        doneKniebeugenAnfänger = view.findViewById(R.id.done_liegestuetz_anfänger);
        addKniegbeugenAnfänger = view.findViewById(R.id.add_liegestuetz_anfänger);

        doneKniebeugenFortgeschritten = view.findViewById(R.id.done_liegestuetz_fortgeschritten);
        addKniebeugenFortgeschritten = view.findViewById(R.id.add_liegestuetz_fortgeschritten);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_challenge_liegestuetz, container, false);

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
            out.print(doneKniebeugenAnfänger.getText().toString() + ";" + doneKniebeugenFortgeschritten.getText().toString());
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
            doneKniebeugenAnfänger.setText(teile[0]);
            doneKniebeugenFortgeschritten.setText(teile[1]);

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