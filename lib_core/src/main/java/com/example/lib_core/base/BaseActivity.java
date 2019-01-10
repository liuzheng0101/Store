package com.example.lib_core.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {
    private boolean inStatus;  //沉浸式
    private boolean isFullScreen;  //全屏
    private Unbinder unbinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayoutId());
        unbinder=ButterKnife.bind(this);
        initView();
        initData();
    }
    protected abstract void initData();

    protected abstract void initView();

    /**
     * 绑定布局id
     * @return
     */
    protected abstract int bindLayoutId();

    /**
     * 显示 toast
     * @param msg
     */
    public void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    /**
     * 无参 跳转
     * @param clazz
     */
    public void startActivity(Class<?> clazz){
        startActivity(new Intent(this,clazz));
    }

    /**
     * 沉浸式
     * @param status
     */
    public void isStatus(boolean status){
        if (status){

        }
    }

    /**
     * 全屏
     * @param fullscreen
     */
    public void isFullscreen(boolean fullscreen){
        if (fullscreen){

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder!=null){
            unbinder.unbind(); //解绑
        }
    }
}
