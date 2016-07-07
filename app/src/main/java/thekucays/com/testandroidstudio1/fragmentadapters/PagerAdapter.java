package thekucays.com.testandroidstudio1.fragmentadapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import thekucays.com.testandroidstudio1.fragments.HomeFragment;
import thekucays.com.testandroidstudio1.fragments.TentangAplikasi;
import thekucays.com.testandroidstudio1.fragments.TentangPembuat;

/**
 * Created by thekucays on 7/7/16.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    int numOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch(position){
            case 0:
                fragment = new TentangAplikasi();
                return fragment;
            case 1:
                fragment = new TentangPembuat();
                return fragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 0;
    }
}
