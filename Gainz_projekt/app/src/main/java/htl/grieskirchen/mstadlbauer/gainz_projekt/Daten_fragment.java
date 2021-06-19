package htl.grieskirchen.mstadlbauer.gainz_projekt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Daten_fragment extends Fragment {

    FloatingActionButton floatingActionButton;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_daten_fragment, container, false);
    }

    private void initButtons(){
        floatingActionButton = getActivity().findViewById(R.id.floatingactionbutton_daten);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        }

}