package com.example.movies.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment>fragments=new ArrayList<>();
    private List<String>tabs_name=new ArrayList<>();

    public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

 /*   public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }*/
    public void addFragment(Fragment fragment,String tab_name)
    {
        fragments.add(fragment);
        tabs_name.add(tab_name);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
            return fragments.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabs_name.get(position);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
