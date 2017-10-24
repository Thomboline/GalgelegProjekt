package dk.thomaslc.galgelegprojekt1;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import src.Galgelogik;

/**
 * Created by Thoma on 10/22/2017.
 */

public class GameFragment extends Fragment {

    private Galgelogik galgelogik;
    private Button submit;
    private EditText answer;
    private TextView failGuess;
    private TextView gameWord;
    private ImageView galge;

    private int[] img = new int[6];


    private static final String TAG = "GameFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.gamefragment_layout, container, false);
        failGuess = v.findViewById(R.id.gameInfo);
        gameWord = v.findViewById(R.id.gameWord);
        answer = v.findViewById(R.id.inputGuessBox);
        submit = v.findViewById(R.id.submitBtn);
        galge = v.findViewById(R.id.imageView);
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
        failGuess.setText("");

        //Put images into array to shuffle through them easily
        img[0] = R.drawable.forkert1;
        img[1] = R.drawable.forkert2;
        img[2] = R.drawable.forkert3;
        img[3] = R.drawable.forkert4;
        img[4] = R.drawable.forkert5;
        img[5] = R.drawable.forkert6;

        //Change the keyboard settings to make the game easier to play
        changeKeyboardFunctionality();

        //Only one button, so setting OnClickListener()
        //like this instead of implementing View.OnClickListener
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitGuess();
            }
        });

    }

    public void updateDisplay() {
        if (galgelogik.getBrugteBogstaver().size() != 0) {
            failGuess.setText("You have " + galgelogik.getAntalForkerteBogstaver() + " wrong guesses. \n" +
                    "\nYou guessed " + galgelogik.getBrugteBogstaver().toString());
            gameWord.setText(galgelogik.getSynligtOrd());
        }
        if (galgelogik.erSidsteBogstavKorrekt()) {
            if (galgelogik.erSpilletVundet()) {
                failGuess.setTextSize(30);
                failGuess.setText("You won!");
                gameWord.setTypeface(null, Typeface.BOLD);
            }
        } else if (!galgelogik.erSidsteBogstavKorrekt()) {
            if (galgelogik.getAntalForkerteBogstaver() != 0) {
                galge.setImageResource(img[galgelogik.getAntalForkerteBogstaver() - 1]);
                if (galgelogik.erSpilletTabt()) {
                    failGuess.setTextSize(50);
                    failGuess.setText("Game over!");
                }
            }
        }
    }

    public void submitGuess() {
        String letter = answer.getText().toString();
        if (letter.length() != 1) {
            answer.setText("");
            answer.setHint("Invalid input");
        } else {
            galgelogik.gætBogstav(letter);
            answer.setText("");
            answer.setHint("Guess 1 letter");
            updateDisplay();
        }

        answer.clearFocus();
        InputMethodManager in = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(answer.getWindowToken(), 0);
    }



    public void changeKeyboardFunctionality() {
        answer.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    submitGuess();
                    handled = true;
                }
                return handled;
            }
        });
    }

}
