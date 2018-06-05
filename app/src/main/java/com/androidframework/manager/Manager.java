package com.androidframework.manager;

import android.support.annotation.NonNull;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.androidframework.api.ApiStores;
import com.androidframework.bean.LoginBean;
import com.androidframework.bean.NearbyInstitutionBean;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by yangpf
 * date:2018/4/11
 * des:艺魔方
 */

public class Manager {
    private static Manager instance;

    public static Manager getInstance() {
        if (null == instance) {
            instance = new Manager();
        }
        return instance;
    }

    private Manager() {

    }


    /**
     * 登录
     */
    public Subscriber<LoginBean> userLogin(@NonNull Map<String, String> map, @NonNull Subscriber<LoginBean> subscribe) {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        RequestBody requestBody = builder.build();
        Observable<LoginBean> observable = NetServiceUtils.getService(ApiStores.class).userLogin(requestBody);
        new NetServiceUtils<LoginBean>().invoke(observable, userLogin, subscribe);
        return subscribe;
    }

    /**
     *
     */
    private Action1<LoginBean> userLogin = LoginBean -> {
        Log.e("========== ", "" + JSON.toJSONString(LoginBean));
    };


    /**
     *
     */
    public Subscriber<NearbyInstitutionBean> nearbyInstitution(@NonNull Map<String, String> map, @NonNull Subscriber<NearbyInstitutionBean> subscribe) {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        RequestBody requestBody = builder.build();

        Observable<NearbyInstitutionBean> observable = NetServiceUtils.getService(ApiStores.class).nearbyInstitution(requestBody);
        new NetServiceUtils<NearbyInstitutionBean>().invoke(observable, nearbyInstitution, subscribe);
        return subscribe;
    }

    /**
     *
     */
    private Action1<NearbyInstitutionBean> nearbyInstitution = TreasureBuyingBean -> {
        Log.e("========== ", "" + JSON.toJSONString(TreasureBuyingBean));
    };
}
