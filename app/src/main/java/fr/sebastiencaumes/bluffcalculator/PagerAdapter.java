package fr.sebastiencaumes.bluffcalculator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * Created by seb on 07/09/2015.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new FragmentBluff();
            case 1:
                return new FragmentSemiBluff();

            case 2:
                return new FragmentCall();

            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
