package com.androidframework.api;


import com.androidframework.bean.LoginBean;
import com.androidframework.bean.NearbyInstitutionBean;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2017/9/13.
 */

public interface ApiStores {
    public static String COMMEN = "app.php?g=App&c=Home_app&a=";

    /**
     * 登录
     *
     * @param data
     * @return
     */
    @POST(COMMEN + "App_is_login")
    Observable<LoginBean> userLogin(@Body RequestBody data);

    /**
     * 网络请求例子
     * @param data
     * @return
     */
    @retrofit2.http.POST("app.php?g=App&c=Meal_app&a=" + "App_middle_footer")
    Observable<NearbyInstitutionBean> nearbyInstitution(@Body RequestBody data);


}
