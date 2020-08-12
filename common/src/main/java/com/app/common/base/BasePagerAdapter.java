package com.app.common.base;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by ${user} on 2018/1/3.
 * QinQin
 */

public class BasePagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private String[] mTitles;

    public BasePagerAdapter(FragmentManager fm, List<Fragment> list, String[] mTitles) {
        super(fm);
        this.mFragments = list;
        this.mTitles = mTitles;
    }

    public void setTitles(String[] mTitles) {
        this.mTitles = mTitles;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}

