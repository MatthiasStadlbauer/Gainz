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
 * Use the {@link ChallengeLiegestuetz#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChallengeLiegestuetz extends Fragment {

    Button buttonRemoveLiegestuetzAnfänger;
    Button buttonAddLiegestuetzAnfänger;
    Button buttonFinalAddLiegestuetzAnfänger;

    Button buttonRemoveLiegestuetzFortgeschritten;
    Button buttonAddLiegestuetzFortgeschritten;
    Button buttonFinalLiegestuetzFortgeschritten;

    TextView doneLiegestuetzAnfänger;
    EditText addLiegestuetzAnfänger;

    TextView doneLiegestuetzFortgeschritten;
    EditText addLiegestuetzFortgeschritten;

    String fileName = "ChallangeLiegestuetzFile";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChallengeLiegestuetz() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChallengeLiegestuetz.
     */
    // TODO: Rename and change types and number of parameters
    public static ChallengeLiegestuetz newInstance(String param1, String param2) {
        ChallengeLiegestuetz fragment = new ChallengeLiegestuetz();
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
        buttonRemoveLiegestuetzAnfänger = view.findViewById(R.id.button_remove_liegestuetz_anfänger);
        buttonRemoveLiegestuetzAnfänger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String liegestuetz = addLiegestuetzAnfänger.getText().toString();
                Integer intLiegestuetz = Integer.parseInt(liegestuetz) - 1;
                addLiegestuetzAnfänger.setText(intLiegestuetz.toString());
            }
        });

        buttonAddLiegestuetzAnfänger = view.findViewById(R.id.button_add_liegestuetz_anfänger);
        buttonAddLiegestuetzAnfänger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String liegestuetz = addLiegestuetzAnfänger.getText().toString();
                Integer intLiegestuetz = Integer.parseInt(liegestuetz) + 1;
                addLiegestuetzAnfänger.setText(intLiegestuetz.toString());
            }
        });

        buttonFinalAddLiegestuetzAnfänger = view.findViewById(R.id.final_button_add_liegestuetz_anfänger);
        buttonFinalAddLiegestuetzAnfänger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String liegestuetzDone = doneLiegestuetzAnfänger.getText().toString();
                String addLiegestuetz = addLiegestuetzAnfänger.getText().toString();
                Integer intLiegestuetzDone = Integer.parseInt(liegestuetzDone) + Integer.parseInt(addLiegestuetz);
                doneLiegestuetzAnfänger.setText(intLiegestuetzDone.toString());
            }
        });
    }

    private void initButtonsFortgeschritten(View view) {
        buttonRemoveLiegestuetzFortgeschritten = view.findViewById(R.id.button_remove_liegestuetz_fortgeschritten);
        buttonRemoveLiegestuetzFortgeschritten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String liegestuetz = addLiegestuetzFortgeschritten.getText().toString();
                Integer intLiegestuetz = Integer.parseInt(liegestuetz) - 1;
                addLiegestuetzFortgeschritten.setText(intLiegestuetz.toString());
            }
        });

        buttonAddLiegestuetzFortgeschritten = view.findViewById(R.id.button_add_liegestuetz_fortgeschritten);
        buttonAddLiegestuetzFortgeschritten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String liegestuetz = addLiegestuetzFortgeschritten.getText().toString();
                Integer intLiegestuetz = Integer.parseInt(liegestuetz) + 1;
                addLiegestuetzFortgeschritten.setText(intLiegestuetz.toString());
            }
        });

        buttonFinalLiegestuetzFortgeschritten = view.findViewById(R.id.final_button_add_liegestuetz_fortgeschritten);
        buttonFinalLiegestuetzFortgeschritten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String liegestuetzDone = doneLiegestuetzFortgeschritten.getText().toString();
                String addLiegestuetz = addLiegestuetzFortgeschritten.getText().toString();
                Integer intLiegestuetzDone = Integer.parseInt(liegestuetzDone) + Integer.parseInt(addLiegestuetz);
                doneLiegestuetzFortgeschritten.setText(intLiegestuetzDone.toString());
            }
        });
    }

    private void initViews(View view) {
        doneLiegestuetzAnfänger = view.findViewById(R.id.done_liegestuetz_anfänger);
        addLiegestuetzAnfänger = view.findViewById(R.id.add_liegestuetz_anfänger);

        doneLiegestuetzFortgeschritten = view.findViewById(R.id.done_liegestuetz_fortgeschritten);
        addLiegestuetzFortgeschritten = view.findViewById(R.id.add_liegestuetz_fortgeschritten);
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
            out.print(doneLiegestuetzAnfänger.getText().toString() + ";" + doneLiegestuetzFortgeschritten.getText().toString());
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
            doneLiegestuetzAnfänger.setText(teile[0]);
            doneLiegestuetzFortgeschritten.setText(teile[1]);

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