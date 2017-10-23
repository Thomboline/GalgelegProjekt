package dk.thomaslc.galgelegprojekt1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import src.Galgelogik;

/**
 * Created by Thoma on 10/22/2017.
 */

public class MainFragment extends Fragment {

    private Galgelogik galgelogik;
    private Button submit;
    private EditText answer;
    private TextView failGuess;
    private TextView gameWord;
    private ImageView galge;

    private int[] img = new int[6];


    private static final String TAG = "MainFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.mainfragment_layout, container, false);
        failGuess = (TextView) v.findViewById(R.id.gameInfo);
        gameWord = (TextView) v.findViewById(R.id.gameWord);
        answer = (EditText) v.findViewById(R.id.inputGuessBox);
        submit = (Button) v.findViewById(R.id.submitBtn);
        galge = (ImageView) v.findViewById(R.id.imageView);
        initGame();
        Log.d(TAG, "onCreateView: Created.");
        return v;
    }

    public void initGame() {
        //Create logic object
        galgelogik = new Galgelogik();

        //Initialize text boxes & set input hint
        gameWord.setText(galgelogik.getSynligtOrd());
        answer.setHint("Guess 1 letter");

        //Put images into array to shuffle through them easily
        img[0] = R.drawable.forkert1;
        img[1] = R.drawable.forkert2;
        img[2] = R.drawable.forkert3;
        img[3] = R.drawable.forkert4;
        img[4] = R.drawable.forkert5;
        img[5] = R.drawable.forkert6;

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String letter = answer.getText().toString();
                if(letter.length() != 1) {
                    answer.setHint("Invalid input");
                }
                else {
                    galgelogik.gætBogstav(letter);
                    answer.setText("");
                    updateDisplay();
                }
            }
        });

   }

    public void updateDisplay() {
        if(galgelogik.getBrugteBogstaver().size() != 0) {
            failGuess.setText("You have " + galgelogik.getAntalForkerteBogstaver() + " wrong guesses. \n" +
                    "You guessed " + galgelogik.getBrugteBogstaver().toString());
            gameWord.setText(galgelogik.getSynligtOrd());
        }
        if(galgelogik.erSidsteBogstavKorrekt()) {
            if(galgelogik.erSpilletVundet()) {

            }

        }
        else if(!galgelogik.erSidsteBogstavKorrekt()) {

            if(galgelogik.getAntalForkerteBogstaver() != 0) {
                galge.setImageResource(img[galgelogik.getAntalForkerteBogstaver()-1]);
            }
        }


    }


}
