package dk.thomaslc.galgelegprojekt1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity implements StartFragment.OnFragSelected {

    private android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
    private android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    private Fragment curFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        curFragment = new StartFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, curFragment);
        fragmentTransaction.commit();
        setContentView(R.layout.activity_main);

    }


    @Override
    public void onFragSelected(int id) {
        switch (id) {
            case R.id.start:
                curFragment = new GameFragment();
                break;
            case R.id.help:
                curFragment = new HelpFragment();
                break;
        }

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, curFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
