package com.androidframework.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidframework.R;
import com.androidframework.bean.NearbyInstitutionBean;
import com.androidframework.manager.Constants;
import com.androidframework.manager.Manager;
import com.androidframework.ui.adapter.NearbyInstitutionsAdapter;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.base.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 */
public class BlankFragment extends BaseFragment {

    protected View rootView;
    protected RecyclerView mSwipeTarget;
    protected SwipeToLoadLayout mSwipeToLoad;
    private NearbyInstitutionsAdapter adapter;
    private int nextPage = 0;
    private String childName = "";
    private List<NearbyInstitutionBean.DataBean> listData = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_blank, container, false);
        postData("juli");
        initView(rootView);
        return rootView;
    }


    /**
     * 请求数据
     * 离我最近,人气最高
     */
    private void postData(String type) {

        HashMap<String, String> map = new HashMap<>();
        map.put("type", type);
        map.put("long", String.valueOf(108.900317));
        map.put("lat", String.valueOf(34.24114));
        map.put("page_number", "10");
        map.put("page", String.valueOf(nextPage));
        map.put("type_kw", childName);
        Manager.getInstance().nearbyInstitution(map, getSubscriber(Constants.NEARBYINSTITUTION));
    }


    private void initView(View rootView) {

        listData = new ArrayList<>();
        //为swipeToLoadLayout设置下拉刷新监听者
        adapter = new NearbyInstitutionsAdapter(R.layout.nearby_institutions_lvpattern, listData, getActivity());


        mSwipeTarget = (RecyclerView) rootView.findViewById(R.id.swipe_target);
        mSwipeToLoad = (SwipeToLoadLayout) rootView.findViewById(R.id.swipe_to_load);


        mSwipeToLoad.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        nextPage = 0;
                        postData("juli");
                        mSwipeToLoad.setRefreshing(false);
                    }
                }, 1000);
            }
        });
        //为swipeToLoadLayout设置上拉加载更多监听者
        mSwipeToLoad.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        nextPage++;
                        postData("juli");
                        mSwipeToLoad.setLoadingMore(false);
                    }
                }, 1000);
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mSwipeTarget.setLayoutManager(manager);
        mSwipeTarget.setAdapter(adapter);

    }


    @Override
    public void onCompleted(int what) {

//        Log.e("333333333333333", "onCompleted");
    }

    @Override
    public void onError(Throwable e, int what) {

//        Log.e("onError", "" + e.getMessage());
    }

    @Override
    public void onNext(Object object, int what) {
        switch (what) {
            case Constants.NEARBYINSTITUTION:
                NearbyInstitutionBean data = (NearbyInstitutionBean) object;
                listData.addAll(data.getData());
                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }
}
