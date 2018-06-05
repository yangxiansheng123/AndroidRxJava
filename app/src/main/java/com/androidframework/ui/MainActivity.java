package com.androidframework.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.androidframework.R;
import com.androidframework.ui.fragment.BlankFragment;
import com.androidframework.ui.fragment.BlankFragment1;
import com.androidframework.ui.fragment.BlankFragment3;
import com.androidframework.utils.T;
import com.base.BaseActivity;
import com.example.administrator.modeltest.ui.BlankFragment2;

import java.util.ArrayList;
import java.util.List;



/**
 * @author yang
 * @date 2018/4/12
 */
public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private static String homepage = "homepageFragment";
    RadioButton rdNearbyInstitution;
    RadioButton rdArtForum;

    /**
     * 创建一个集合，存储碎片
     */
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private BlankFragment homePageFragment;
    private BlankFragment1 artForumFragment;
    private BlankFragment2 nearbyInstitutionsFragment;
    private BlankFragment3 meFragment;

    private FragmentManager fm;
    FragmentTransaction transaction;
    private RadioGroup mRadioButtonRg;
    private FragmentTransaction transaction1;
    private RadioButton mRadioButton;
    private long firstTime = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            homePageFragment = new BlankFragment();
            fragmentManager.beginTransaction().replace(R.id.fl, homePageFragment, homepage).commit();
        }
    }

    /**
     * 初始化控件
     */
    private void initView() {
        rdNearbyInstitution = (RadioButton) findViewById(R.id.rd_nearby_institution);
        rdArtForum = (RadioButton) findViewById(R.id.rd_art_forum);
        mRadioButtonRg = (RadioGroup) findViewById(R.id.rg);
        mRadioButton = (RadioButton) findViewById(R.id.rd_me);
        mRadioButtonRg.setOnCheckedChangeListener(this);
        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        homePageFragment = (BlankFragment) fm.findFragmentByTag(homepage);
        artForumFragment = (BlankFragment1) fm.findFragmentByTag("artForumFragment");
        nearbyInstitutionsFragment = (BlankFragment2) fm.findFragmentByTag("nearbyInstitutionsFragment");
        meFragment = (BlankFragment3) fm.findFragmentByTag("meFragment");
    }

    /**
     * tab切换事件处理
     *
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        transaction1 = fm.beginTransaction();
        if (homePageFragment != null) {
            transaction1.hide(homePageFragment);
        }
        if (artForumFragment != null) {
            transaction1.hide(artForumFragment);
        }
        if (nearbyInstitutionsFragment != null) {
            transaction1.hide(nearbyInstitutionsFragment);
        }
        if (meFragment != null) {
            transaction1.hide(meFragment);
        }
        switch (checkedId) {
            case R.id.rd_home_page:
                if (homePageFragment == null) {
                    homePageFragment = new BlankFragment();
                    transaction1.add(R.id.fl, homePageFragment, homepage);
                } else {
                    transaction1.show(homePageFragment);
                }
                break;
            case R.id.rd_art_forum:

                if (artForumFragment == null) {
                    artForumFragment = new BlankFragment1();
                    transaction1.add(R.id.fl, artForumFragment, "artForumFragment");
                } else {
                    transaction1.show(artForumFragment);
                }
                break;
            case R.id.rd_nearby_institution:

                if (nearbyInstitutionsFragment == null) {
                    nearbyInstitutionsFragment = new BlankFragment2();
                    transaction1.add(R.id.fl, nearbyInstitutionsFragment, "nearbyInstitutionsFragment");
                } else {
                    transaction1.show(nearbyInstitutionsFragment);
                }
                break;
            case R.id.rd_me:
                if (meFragment == null) {
                    meFragment = new BlankFragment3();
                    transaction1.add(R.id.fl, meFragment, "meFragment");
                } else {
                    transaction1.show(meFragment);
                }
                break;
                default:
                    break;
        }
        transaction1.commit();
    }


    /**
     * 监听手机物理键（返回）
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long secondTime = System.currentTimeMillis();
            //如果两次按键时间间隔大于2秒，则不退出
            if (secondTime - firstTime > 2000) {
                T.showShort(MainActivity.this, "再按一次退出程序");
                //更新firstTime
                firstTime = secondTime;
                return true;
            } else {
                finish();
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 检查版本更新
     */
    private void updateVersion() {
//        mProgressDialog = ProgressDialog.show(this, null, getString(R.string.dialog));
//        mProgressDialog.setCanceledOnTouchOutside(true);

//        UpdateVersionBean data = new UpdateVersionBean();
//        data.setVersion_code(AppUtils.getAppVersionCode(this) + "");
//        data.setPlatform("android");
//        data.setToken("token");
//        Gson gson = new Gson();
//        String dataUpdate = gson.toJson(data);
//        MYManager.getInstance().updateVersion(dataUpdate, getSubscriber(Constants.UPDATEVERSION));
//        Log.e("tttttttttttttttt", token);

//        IsUpdateApk isUpdateApk = new IsUpdateApk(MainActivity.this);
//        isUpdateApk.isupdate(Integer.parseInt("updateVeersion.getVersion_code()"),
//                "updateVeersion.getDownload_path()",
//                "版本更新",
//                R.mipmap.ic_launcher);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
