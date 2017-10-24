package dk.thomaslc.galgelegprojekt1;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Thoma on 10/23/2017.
 */

public class StartFragment extends Fragment implements View.OnClickListener {

    private OnFragSelected mCallback;
    private Button start, help;
    private ImageView hangmanImage;
    private TextView title;

    public interface OnFragSelected {
        void onFragSelected(int id);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnFragSelected) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnFragSelected interface..");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.startfragment_layout, container, false);
        start = view.findViewById(R.id.start);
        help = view.findViewById(R.id.help);
        hangmanImage = view.findViewById(R.id.hangman);
        title = view.findViewById(R.id.title);
        initLayout();

        return view;
    }

    public void initLayout() {
        start.setOnClickListener(this);
        help.setOnClickListener(this);
        start.setText("Start");
        help.setText("Help");
        hangmanImage.setImageResource(R.drawable.forkert6);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start:
                mCallback.onFragSelected(R.id.start);
                break;
            case R.id.help:
                mCallback.onFragSelected(R.id.help);
                break;
        }
    }
}
