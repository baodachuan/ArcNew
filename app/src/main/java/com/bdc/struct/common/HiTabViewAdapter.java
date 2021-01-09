package com.bdc.struct.common;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bdc.bui.tab.bottom.HiTabBottomInfo;

import java.util.List;

public class HiTabViewAdapter {
    private List<HiTabBottomInfo<?>> mInfoList;
    private Fragment mCurrentFragment;
    private FragmentManager mFragmentManager;

    public HiTabViewAdapter(List<HiTabBottomInfo<?>> infoList, FragmentManager fragmentManager) {
        this.mInfoList = infoList;
        this.mFragmentManager = fragmentManager;
    }

    public void instaniateItem(View container, int position) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        if (mCurrentFragment != null) {
            ft.hide(mCurrentFragment);
        }
        String name = container.getId() + ":" + position;
        Fragment fragment = mFragmentManager.findFragmentByTag(name);
        if (fragment != null) {
            ft.show(fragment);
        } else {
            fragment = getItem(position);
            if (!fragment.isAdded()) {
                ft.add(container.getId(), fragment, name);
            }
        }
        mCurrentFragment = fragment;
        ft.commitNowAllowingStateLoss();
    }

    public Fragment getCurrentFragment() {
        return mCurrentFragment;
    }

    public Fragment getItem(int position) {
        try {
            return mInfoList.get(position).fragmet.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getCount() {
        return mInfoList == null ? 0 : mInfoList.size();
    }

}
