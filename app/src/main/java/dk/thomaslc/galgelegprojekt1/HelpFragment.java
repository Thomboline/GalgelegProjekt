package dk.thomaslc.galgelegprojekt1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Thoma on 10/22/2017.
 */

public class HelpFragment extends Fragment{

    private static final String TAG = "HelpFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.helpfragment_layout, container, false);
        Log.d(TAG, "onCreateView: Created.");
        return view;
    }
}
