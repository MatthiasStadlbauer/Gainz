package htl.grieskirchen.mstadlbauer.gainz_projekt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        return view;
    }
}