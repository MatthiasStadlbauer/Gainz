package htl.grieskirchen.mstadlbauer.gainz_projekt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

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

        return view;
    }
}