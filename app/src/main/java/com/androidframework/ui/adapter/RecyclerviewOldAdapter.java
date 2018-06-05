package com.androidframework.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidframework.R;
import com.androidframework.bean.LoginBean;
import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

/**
 * @author yang
 * recycleview 适配器
 */

public class RecyclerviewOldAdapter extends RecyclerView.Adapter<RecyclerviewOldAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private List<LoginBean> listData;
    private MontherKnowledgeListener mKnowledgeListener;


    public RecyclerviewOldAdapter(Context mContext, MontherKnowledgeListener knowledgeListener) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        mKnowledgeListener = knowledgeListener;
    }

    public void setListData(List<LoginBean> listData) {
        this.listData = listData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.activity_main, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        Glide.with(mContext)
                .load("http://slkkdj")
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .fitCenter()
                .dontAnimate()
                .into(holder.img_bg_headimng);
//        holder.tv_g_name.setText("Title");
    }

    @Override
    public int getItemCount() {
        return listData.size() > 0 ? listData.size() : 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView img_bg_headimng;
        TextView textView;

        public ViewHolder(View view) {
            super(view);
//            img_bg_headimng = (RoundedImageView) view.findViewById(R.id.img_bg_headimng);
        }
    }

    /**
     * 接口回调
     */
    public interface MontherKnowledgeListener {
        public void monterKonowledge(String cat_id);
    }
}
