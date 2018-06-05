package com.androidframework.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidframework.R;
import com.androidframework.bean.NearbyInstitutionBean;
import com.androidframework.utils.SpanUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 * 附近机构
 */

public class NearbyInstitutionsAdapter extends BaseQuickAdapter<NearbyInstitutionBean.DataBean, BaseViewHolder> {
    private Context mContext;

    public NearbyInstitutionsAdapter(@LayoutRes int layoutResId, @Nullable List<NearbyInstitutionBean.DataBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, NearbyInstitutionBean.DataBean data) {
        ImageView institution = baseViewHolder.getView(R.id.img_institution);
        ImageView img_rebackCash = baseViewHolder.getView(R.id.img_rebackCash);//返现
        TextView institutionName = baseViewHolder.getView(R.id.tv_institutionName);//机构名称
        TextView coursePrice = baseViewHolder.getView(R.id.tv_coursePrice);//课程价格
        TextView course0 = baseViewHolder.getView(R.id.tv_course0);//专业
        TextView course1 = baseViewHolder.getView(R.id.tv_course1);//专业
        TextView course2 = baseViewHolder.getView(R.id.tv_course2);//专业
        TextView distance = baseViewHolder.getView(R.id.tv_distance);//距离
        TextView address = baseViewHolder.getView(R.id.tv_address);//地址

        String feature = data.getFeature();
        String[] features = feature.split(",");
        int length = features.length;
        if (data.getPic_info() != null) {
            Glide.with(mContext)
                    .load(data.getPic_info())
                    .placeholder(R.mipmap.ic_launcher)
                    .crossFade()
                    .fitCenter()
                    .into(institution);
        }

        //0未返现 1返现
        if (data.getPaycash().equals("0")) {
            img_rebackCash.setVisibility(View.GONE);
        } else {
            img_rebackCash.setVisibility(View.VISIBLE);
        }
        institutionName.setText(data.getName());
        if (1 == length) {
            course0.setText(features[0]);
            course0.setVisibility(View.VISIBLE);
            course1.setVisibility(View.GONE);
            course2.setVisibility(View.GONE);
        } else if (length == 2) {
            course0.setText(features[0]);
            course1.setText(features[1]);
            course0.setVisibility(View.VISIBLE);
            course1.setVisibility(View.VISIBLE);
        } else if (length == 3) {
            course0.setText(features[0]);
            course1.setText(features[1]);
            course2.setText(features[2]);
            course0.setVisibility(View.VISIBLE);
            course1.setVisibility(View.VISIBLE);
            course2.setVisibility(View.VISIBLE);
        } else {
            course0.setVisibility(View.GONE);
            course1.setVisibility(View.GONE);
            course2.setVisibility(View.GONE);
        }
        distance.setText(data.getJuli());
        coursePrice.setText(new SpanUtils().append("约课:").setForegroundColor(Color.rgb(35, 24, 21))
                .append(data.getNum()).setForegroundColor(Color.rgb(230, 0, 18))
                .append("/￥").setForegroundColor(Color.rgb(35, 24, 21))
                .append(data.getPrice() + "元").setForegroundColor(Color.rgb(230, 0, 18)).create());
        address.setText("地址:" + data.getAdress());
    }
}
