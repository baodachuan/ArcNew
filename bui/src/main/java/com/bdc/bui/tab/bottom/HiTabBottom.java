package com.bdc.bui.tab.bottom;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

import com.bdc.bui.R;
import com.bdc.bui.tab.common.IHiTab;


public class HiTabBottom extends RelativeLayout implements IHiTab<HiTabBottomInfo<?>> {

    private HiTabBottomInfo<?> mTabInfo;
    private ImageView mTabImageView;
    private TextView mTabIconView;
    private TextView mTabNameView;

    public HiTabBottom(Context context) {
        this(context,null);
    }

    public HiTabBottom(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HiTabBottom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.hi_tab_bottom,this);
        mTabImageView=findViewById(R.id.iv_image);
        mTabIconView=findViewById(R.id.tv_icon);
        mTabNameView=findViewById(R.id.tv_name);
    }


    @Override
    public void setHiTabInfo(@NonNull HiTabBottomInfo<?> data) {
        this.mTabInfo=data;
        inflateInfo(false,true);

    }

    private  void inflateInfo(boolean selected,boolean init){
        if(mTabInfo.tabType == HiTabBottomInfo.TabType.ICON){
            if(init){
                mTabImageView.setVisibility(GONE);
                mTabIconView.setVisibility(VISIBLE);
                Typeface typeface= Typeface.createFromAsset(getContext().getAssets(),mTabInfo.iconFont);
                mTabIconView.setTypeface(typeface);
                if(!TextUtils.isEmpty(mTabInfo.name)){
                    mTabNameView.setText(mTabInfo.name);
                }
            }
            if(selected){
                mTabIconView.setText(TextUtils.isEmpty(mTabInfo.selectedIconName)? mTabInfo.defaultIconName:mTabInfo.selectedIconName);
                mTabNameView.setTextColor(getTextColor(mTabInfo.tintColor));
                mTabIconView.setTextColor(getTextColor(mTabInfo.tintColor));
            }else{
                mTabIconView.setText(mTabInfo.defaultIconName);
                mTabIconView.setTextColor(getTextColor(mTabInfo.defaultColor));
                mTabNameView.setTextColor(getTextColor(mTabInfo.defaultColor));
            }
        }else if(mTabInfo.tabType==HiTabBottomInfo.TabType.BITMAP){
            if(init){
                mTabImageView.setVisibility(VISIBLE);
                mTabIconView.setVisibility(GONE);
                if(!TextUtils.isEmpty(mTabInfo.name)){
                    mTabNameView.setText(mTabInfo.name);
                }
            }
            if(selected){
                mTabImageView.setImageBitmap(mTabInfo.selectedBitmap);
            }else {
                mTabImageView.setImageBitmap(mTabInfo.defaultBitmap);
            }
        }

    }

    @ColorInt
    private int getTextColor(Object color) {
        if(color instanceof String){
            return Color.parseColor((String)color);
        }
        return (int) color;
    }

    @Override
    public void resetHeight(int height) {
        ViewGroup.LayoutParams layoutParams=getLayoutParams();
        layoutParams.height=height;
        setLayoutParams(layoutParams);
        mTabNameView.setVisibility(GONE);

    }

    @Override
    public void onTabSelectedChange(int index, @NonNull Object preInfo, @NonNull Object nextInfo) {
        if((preInfo!=mTabInfo && nextInfo!=mTabInfo)|| preInfo==nextInfo){
            return;
        }
        if(preInfo==mTabInfo){
            inflateInfo(false,false);
        }
        else {
            inflateInfo(true,false);
        }

    }

}
