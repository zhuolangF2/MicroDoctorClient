package com.zhuolang.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener,ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private List<Fragment> mTabs = new ArrayList<Fragment>();
//    private String[] mTitles = new String[]{ "First Fragment !", "Second Fragment !" , "Third Fragment !" };
    private FragmentPagerAdapter mAdapter;

    HomepageTabFragment homepageTabFragment = new HomepageTabFragment();
    ShareTabFragment shareTabFragment = new ShareTabFragment();
    MeTabFragment meTabFragment = new MeTabFragment();

    private List<ChangeColorIconWithText> mTabIndicators = new ArrayList<ChangeColorIconWithText>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setOverflowButtonAlways();
//        getActionBar().setDisplayShowHomeEnabled(false);
        Log.d("activityID","这个是----------:"+this.toString());
        initView();//获取viewpager
        initDatas();//初始化数据
        mViewPager.setAdapter(mAdapter);//用adapter为viewpager赋值
        initEvent();

    }

    /**
     * 初始化所有事件
     */
    private void initEvent() {
        mViewPager.setOnPageChangeListener(this);

    }

    private void initDatas() {//为fragment传输数据
        mTabs.add(homepageTabFragment);
        mTabs.add(shareTabFragment);
        mTabs.add(meTabFragment);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount(){
                return mTabs.size();
            }

            @Override
            public Fragment getItem(int position){
                return mTabs.get(position);
            }
        };
    }

    private void initView(){
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        ChangeColorIconWithText one = (ChangeColorIconWithText) findViewById(R.id.id_indicator_one);
        mTabIndicators.add(one);
        ChangeColorIconWithText two = (ChangeColorIconWithText) findViewById(R.id.id_indicator_two);
        mTabIndicators.add(two);
        ChangeColorIconWithText three = (ChangeColorIconWithText) findViewById(R.id.id_indicator_three);
        mTabIndicators.add(three);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);

        one.setIconAlpha(1.0f);

    }

    @Override
    public void onClick(View v) {
        clickTab(v);

    }

    /**
     * 点击Tab按钮，重置其他页面tab的透明度，并设置该页面透明度为1
     *
     * @param v
     */
    private void clickTab(View v){
        resetOtherTabs();

        switch (v.getId()){
            case R.id.id_indicator_one:
                mTabIndicators.get(0).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(0, false);
                break;
            case R.id.id_indicator_two:
                mTabIndicators.get(1).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(1, false);
                break;
            case R.id.id_indicator_three:
                mTabIndicators.get(2).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(2, false);
                break;
        }
    }

    /**
     * 重置其他的TabIndicator的颜色
     */
    private void resetOtherTabs(){
        for (int i = 0; i < mTabIndicators.size(); i++){
            mTabIndicators.get(i).setIconAlpha(0);
        }
    }

    /*
     *从左到右和从右到左滑动，透明度变化
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // Log.e("TAG", "position = " + position + " ,positionOffset =  "
        // + positionOffset);
        if (positionOffset > 0) {
            ChangeColorIconWithText left = mTabIndicators.get(position);
            ChangeColorIconWithText right = mTabIndicators.get(position + 1);
            left.setIconAlpha(1 - positionOffset);
            right.setIconAlpha(positionOffset);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

