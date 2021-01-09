package com.bdc.struct;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.FragmentManager;

import com.bdc.bui.tab.bottom.HiTabBottomInfo;
import com.bdc.bui.tab.bottom.HiTabBottomLayout;
import com.bdc.bui.tab.common.IHiTab;
import com.bdc.bui.tab.common.IHiTabLayout;
import com.bdc.struct.common.HiFragmentTabView;
import com.bdc.struct.common.HiTabViewAdapter;
import com.bdc.struct.fragment.CategoryFragment;
import com.bdc.struct.fragment.FavoriteFragment;
import com.bdc.struct.fragment.HomePageFragment;
import com.bdc.struct.fragment.ProfileFragment;
import com.bdc.struct.fragment.RecommandFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivityLogic {
    public static final String SAVED_CURRENT_ID="save_current_id";
    private HiFragmentTabView fragmentTabView;
    private HiTabBottomLayout hiTabBottomLayout;
    private List<HiTabBottomInfo<?>> infoList;
    private ActivityProvider activityProvider;
    private int currentItemIndex;

    public MainActivityLogic(ActivityProvider activityProvider,Bundle saveInstanceState) {
        this.activityProvider = activityProvider;
        if(saveInstanceState!=null){
            currentItemIndex= saveInstanceState.getInt(SAVED_CURRENT_ID);
        }
        initTabBottom();
    }

    public HiFragmentTabView getFragmentTabView() {
        return fragmentTabView;
    }

    public HiTabBottomLayout getHiTabBottomLayout() {
        return hiTabBottomLayout;
    }

    public List<HiTabBottomInfo<?>> getInfoList() {
        return infoList;
    }

    private void initTabBottom(){
        hiTabBottomLayout =activityProvider.findViewById(R.id.tab_bottom_layout);
        hiTabBottomLayout.setTabAlpha(0.85f);
        infoList=new ArrayList<>();
        int defaultColor=activityProvider.getResources().getColor(R.color.tabBottomDefaultColor);
        int tintColor=activityProvider.getResources().getColor(R.color.tabBottomTintColor);


        HiTabBottomInfo homeInfo=new HiTabBottomInfo(
                "首页","fonts/iconfont.ttf",
                activityProvider.getString(R.string.if_home),null,
                defaultColor,tintColor
        );
        homeInfo.fragmet = HomePageFragment.class;

        HiTabBottomInfo infoFavor=new HiTabBottomInfo<>(
                "收藏","fonts/iconfont.ttf",
                activityProvider.getString(R.string.if_favorite),null,
                defaultColor,tintColor
        );
        infoFavor.fragmet= FavoriteFragment.class;

        HiTabBottomInfo infoProfile=new HiTabBottomInfo<>(
                "我的","fonts/iconfont.ttf",
                activityProvider.getString(R.string.if_profile),null,
                defaultColor,tintColor
        );
        infoProfile.fragmet= ProfileFragment.class;

        HiTabBottomInfo infoCategory=new HiTabBottomInfo<>(
                "分类","fonts/iconfont.ttf",
                activityProvider.getString(R.string.if_category),null,
                defaultColor,tintColor
        );
        infoCategory.fragmet= CategoryFragment.class;

        HiTabBottomInfo infoRecommend = new HiTabBottomInfo<>(
                "推荐",
                "fonts/iconfont.ttf",
                activityProvider.getString(R.string.if_recommend),
                null,
                defaultColor,
                tintColor
        );
        infoRecommend.fragmet = RecommandFragment.class;

        infoList.add(homeInfo);
        infoList.add(infoFavor);
        infoList.add(infoCategory);
        infoList.add(infoRecommend);
        infoList.add(infoProfile);

        hiTabBottomLayout.inflateInfo(infoList);
        initFragmentTabView();
        hiTabBottomLayout.addTabSelectedChangedListener(new IHiTabLayout.OnTabSelectedListener<HiTabBottomInfo<?>>() {
            @Override
            public void onTabSelectedChange(int index, @NonNull HiTabBottomInfo<?> preInfo, @NonNull HiTabBottomInfo<?> nextInfo) {
                fragmentTabView.setCurrentItem(index);
                MainActivityLogic.this.currentItemIndex=index;
            }
        });
        hiTabBottomLayout.defaultSelected(infoList.get(currentItemIndex));


    }

    private void initFragmentTabView(){
        HiTabViewAdapter tabViewAdapter=new HiTabViewAdapter(infoList,activityProvider.getSupportFragmentManager());
        fragmentTabView = activityProvider.findViewById(R.id.fragment_tab_view);
        fragmentTabView.setAdapter(tabViewAdapter);
    }

    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(SAVED_CURRENT_ID, currentItemIndex);
    }

    public interface ActivityProvider {
        <T extends View> T findViewById(@IdRes int id);
        Resources getResources();
        FragmentManager getSupportFragmentManager();
        String getString (@StringRes int resId);
    }

}
