package nsu.cse299parkprovider;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nsu.cse299parkprovider.R;

/**
 * Created by Shanto on 2/21/2018.
 */

public class FirstIntro extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_intro_one, container, false);




        return rootView;
    }
}
