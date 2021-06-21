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

        return view;
    }
}