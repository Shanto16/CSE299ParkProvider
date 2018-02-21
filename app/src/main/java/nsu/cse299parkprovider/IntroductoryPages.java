package nsu.cse299parkprovider;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.Button;

import nsu.cse299parkprovider.R;


public class IntroductoryPages extends AppCompatActivity {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout tabLayout;

    private Button btn_get_started;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory_pages);


        /***
         * Initializing widgets
         * ***/
        mSectionsPagerAdapter = new SectionsPagerAdapter(this.getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container_introductory);
        tabLayout = (TabLayout) findViewById(R.id.tabDots);
        btn_get_started = findViewById(R.id.getStarted);
        tabLayout.setupWithViewPager(mViewPager, true);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        /***
         * Starting login page from getting started button
          */
        btn_get_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IntroductoryPages.this,LoginPage.class));
            }
        });
    }


    /***
     * Creating adapter for viewpager that is swipe-able
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    FirstIntro one = new FirstIntro(); //Making an object FirstIntro page to refer in the adapter
                    return one;
                case 1:
                    SecondIntro two = new SecondIntro();
                    return two;
                case 2:
                    ThirdIntro three = new ThirdIntro();
                    return three;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        } //Number 3 means total page is 3

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return ""; // "" means the titles will be blank.
                case 1:
                    return "";
                case 2:
                    return "";
            }
            return null;
        }
    }

}
