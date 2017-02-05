package app.sungi.horoscope.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import app.sungi.horoscope.R;
import app.sungi.horoscope.fragment_sign.TabFragmentMonth;
import app.sungi.horoscope.fragment_sign.TabFragmentWeek;
import app.sungi.horoscope.fragment_sign.TabFragmentYear;

public class OneSignActivity extends AppCompatActivity {

    Toolbar toolbar;
    Intent intent;
    static String SELECTED_ZODIAC_SIGN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_sign);

        intent = getIntent();
        SELECTED_ZODIAC_SIGN = intent.getStringExtra("SELECTED_ZODIAC_SIGN");

        setupToolbar();

        setupViewPager();

        setupCollapsingToolbar();

    }

    private void setupCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(
                R.id.collapse_toolbar);
        collapsingToolbar.setTitleEnabled(false);
    }

    private void setupViewPager() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @SuppressWarnings("ConstantConditions")
    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Гороскоп");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupViewPager(ViewPager viewPager) {
        OneSignActivity.ViewPagerAdapter adapter = new OneSignActivity.ViewPagerAdapter(getSupportFragmentManager());


        adapter.addFrag(new TabFragmentWeek(), "На неделю");
        adapter.addFrag(new TabFragmentMonth(), "На месяц");
        adapter.addFrag(new TabFragmentYear(), "На год");

        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
    }




    static class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            Bundle bundle = new Bundle();
            bundle.putString("SIGN", OneSignActivity.SELECTED_ZODIAC_SIGN);
            fragment.setArguments(bundle);
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
