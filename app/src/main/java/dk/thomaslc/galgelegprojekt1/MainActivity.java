package dk.thomaslc.galgelegprojekt1;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SectionsPageAdapter pageAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        //Setup viewpager with sections adapter
        viewPager = (ViewPager) findViewById(R.id.container);
        initViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void initViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new MainFragment(), "Main");
        adapter.addFragment(new HelpFragment(), "Help");
        adapter.addFragment(new AboutFragment(), "About");
        viewPager.setAdapter(adapter);
    }
}
