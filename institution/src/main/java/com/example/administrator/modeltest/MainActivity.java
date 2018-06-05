package com.example.administrator.modeltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.base.BaseActivity;
import com.example.administrator.modeltest.ui.BlankFragment2;

/**
 * @author yang
 * 这个模块可以单独运行 需要在config.gradle 文件中配置
 * isModule = flase 可单独运行,否则只能依赖app工程
 */
public class MainActivity extends BaseActivity {

    private BlankFragment2 blankFragment2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        blankFragment2 = new BlankFragment2();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl, blankFragment2).commit();
    }
}
