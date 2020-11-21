package com.bdc.blibrary.log;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bdc.blibrary.Util.HiDisplayUtil;

public class HiViewPrinterProvider {
    public static final String TAG_FLOATING_VIEW = "TAG_FLOATING_VIEW";
    public static final String TAG_LOG_VIEW = "TAG_LOG_VIEW";

    private FrameLayout rootView;
    private View floatingView;
    private boolean isOpen;
    private FrameLayout mLogView;
    private RecyclerView recyclerView;

    public HiViewPrinterProvider(FrameLayout rootView, RecyclerView recyclerView) {
        this.rootView = rootView;
        this.recyclerView = recyclerView;
    }

    public void showFloatingView() {
        if (rootView.findViewWithTag(TAG_FLOATING_VIEW) != null) {
            return;
        }
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.BOTTOM | Gravity.END;
        View floatingView =genFloatingView();
        floatingView.setTag(TAG_FLOATING_VIEW);
        floatingView.setBackgroundColor(Color.BLACK);
        floatingView.setAlpha(0.8f);
        params.bottomMargin=HiDisplayUtil.dp2px(100,recyclerView.getResources());
        rootView.addView(genFloatingView(),params);
    }

    public View genFloatingView(){
        if(floatingView!=null){
            return floatingView;
        }
        TextView textView=new TextView(rootView.getContext());
        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!isOpen){
                    showLogView();
                }
            }
        });
        textView.setText("HiLog");
        return floatingView=textView;

    }

    private void showLogView() {
        if(rootView.findViewWithTag(TAG_LOG_VIEW)!=null){
            return;
        }
        FrameLayout.LayoutParams params=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                HiDisplayUtil.dp2px(160,rootView.getResources()));
        params.gravity = Gravity.BOTTOM;
        View logView = genLogView();
        logView.setTag(TAG_LOG_VIEW);
        rootView.addView(genLogView(),params);
        isOpen=true;
    }

    private View genLogView() {
        if(mLogView !=null){
            return mLogView;
        }
        FrameLayout logview =new FrameLayout(rootView.getContext());
        logview.setBackgroundColor(Color.BLACK);
        logview.addView(recyclerView);
        FrameLayout.LayoutParams params =new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity=Gravity.END;
        final TextView closeView=new TextView(rootView.getContext());
        closeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeLogView();
            }
        });
        closeView.setText("close");
        logview.addView(closeView,params);
        return this.mLogView =logview;

    }

    private void closeLogView() {
        isOpen=false;
        rootView.removeView(genLogView());

    }

    public void closeFloatingView(){
        rootView.removeView(genFloatingView());
    }



}
