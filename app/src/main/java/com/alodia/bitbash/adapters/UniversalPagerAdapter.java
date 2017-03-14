package com.alodia.bitbash.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class UniversalPagerAdapter extends FragmentPagerAdapter {

    private int pageCount;
    // Tab Titles
    private ArrayList<String> tabtitles;
    private ArrayList<Fragment> fragmentList;

    public UniversalPagerAdapter(FragmentManager fm, int _pageCount, ArrayList<String> _tabTitles, ArrayList<Fragment> _fragmentList) {
        super(fm);

        tabtitles = _tabTitles;
        pageCount = _pageCount;
        fragmentList = _fragmentList;
    }

    @Override
    public int getCount() {
        return pageCount;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabtitles.get(position);
    }

}
