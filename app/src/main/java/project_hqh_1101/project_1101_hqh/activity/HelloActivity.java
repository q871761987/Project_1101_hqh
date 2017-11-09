package project_hqh_1101.project_1101_hqh.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

import project_hqh_1101.project_1101_hqh.R;
import project_hqh_1101.project_1101_hqh.fragment.Fragment1;
import project_hqh_1101.project_1101_hqh.fragment.Fragment2;
import project_hqh_1101.project_1101_hqh.fragment.Fragment3;

public class HelloActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private RadioButton [] radioButtons  = new RadioButton[3];
    private int [] radioButtonsId  = {R.id.rb1,R.id.rb2,R.id.rb3};
    private List<Fragment> fragmentList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        noTitle();
        initVIew();
        initData();
        initListener();
    }

    private void initListener() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                radioButtons[position].setChecked(true);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        fragmentList.add(new Fragment1());
        fragmentList.add(new Fragment2());
        fragmentList.add(new Fragment3());
        FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }
        };
        viewPager.setAdapter(pagerAdapter);
    }

    private void initVIew() {
        viewPager = (ViewPager) this.findViewById(R.id.viewpater);
        for(int i=0;i<radioButtons.length;i++)
        {
            radioButtons[i] = (RadioButton) this.findViewById(radioButtonsId[i]);
            radioButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(int i=0;i<radioButtons.length;i++)
                    {
                        if(v==radioButtons[i])
                        {
                            viewPager.setCurrentItem(i);
                        }
                    }
                }
            });
        }
    }
    public void noTitle()
    {
        ActionBar supportActionBar = getSupportActionBar();
        if(supportActionBar!=null)
        {
            supportActionBar.hide();
        }
    }
}
