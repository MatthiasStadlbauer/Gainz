package htl.grieskirchen.mstadlbauer.gainz_projekt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ChallengeView_fragment extends Fragment {

    /**
     * Fragment für die Challenges
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_challenge_view_fragment, container, false);
        return view;
    }
}