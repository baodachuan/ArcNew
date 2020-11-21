package com.bdc.blibrary.log;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bdc.blibrary.R;

import java.util.ArrayList;
import java.util.List;

public class HiViewPrinter implements HiLogPrinter {
    private RecyclerView mRecyclerView;
    private LogAdapter mLogAdapter;
    private HiViewPrinterProvider viewProvider;

    public HiViewPrinter(Activity activity) {
        FrameLayout rootView= activity.findViewById(android.R.id.content);
        mRecyclerView=new RecyclerView(activity);
        mLogAdapter=new LogAdapter(LayoutInflater.from(mRecyclerView.getContext()));
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(mRecyclerView.getContext());

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mLogAdapter);

        viewProvider =new HiViewPrinterProvider(rootView,mRecyclerView);
    }

    public HiViewPrinterProvider getViewProvider() {
        return viewProvider;
    }

    @Override
    public void print(@NonNull HiLogConfig config, int level, String tag, @NonNull String printString) {

        mLogAdapter.addItem(new HiLogMo(System.currentTimeMillis(),level,tag,printString));
        mRecyclerView.smoothScrollToPosition(mLogAdapter.getItemCount()-1);
    }

    private static class LogAdapter extends RecyclerView.Adapter<LogViewHoder>{
        private LayoutInflater inflater;
        private List<HiLogMo> logs=new ArrayList<>();

        public LogAdapter(LayoutInflater inflater) {
            this.inflater = inflater;
        }
        void addItem(HiLogMo logItem){
            logs.add(logItem);
            notifyItemInserted(logs.size()-1);
        }

        @NonNull
        @Override
        public LogViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = inflater.inflate(R.layout.hilog_item,parent,false);
            return new LogViewHoder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull LogViewHoder holder, int position) {
            HiLogMo logItem=logs.get(position);
            int color=getHighLightColor(logItem.level);

            holder.tagView.setTextColor(color);
            holder.msgView.setTextColor(color);

            holder.tagView.setText(logItem.getFlattened());
            holder.msgView.setText(logItem.log);

        }
        private int getHighLightColor(int logLevel){
            int highlight;
            switch (logLevel) {
                case HiLogType.V:
                    highlight = 0xffbbbbbb;
                    break;
                case HiLogType.D:
                    highlight = 0xffffffff;
                    break;
                case HiLogType.I:
                    highlight = 0xff6a8759;
                    break;
                case HiLogType.W:
                    highlight = 0xffbbb529;
                    break;
                case HiLogType.E:
                    highlight = 0xffff6b68;
                    break;
                default:
                    highlight = 0xffffff00;
                    break;
            }
            return highlight;
        }


        @Override
        public int getItemCount() {
            return logs.size();
        }
    }

    private static class LogViewHoder extends RecyclerView.ViewHolder{
        TextView tagView;
        TextView msgView;


        public LogViewHoder(@NonNull View itemView) {
            super(itemView);
            tagView=itemView.findViewById(R.id.tag);
            msgView=itemView.findViewById(R.id.message);
        }
    }
}
