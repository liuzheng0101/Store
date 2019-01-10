package com.example.mature.store.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mature.store.R;
import com.example.mature.store.bean.UserEntity;
import com.example.mature.store.contract.StoreContract;
import com.example.mature.store.presenter.StorePresenter;

import java.util.HashMap;

import butterknife.BindView;

public class RegActivity extends AppCompatActivity implements StoreContract.IStoreView {
    private EditText phone;
    private EditText password;
    private CheckBox cb_eye;
    private StorePresenter storePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        password=findViewById(R.id.password);
        phone=findViewById(R.id.phone);
        cb_eye=findViewById(R.id.cb_eye);
        initData();
        //明文转化事件
        cb_eye.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    //设置为明文显示
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    //设置为密文显示
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                //光标在最后显示
                password.setSelection(password.length());
            }
        });
    }
    private void initData() {
        storePresenter=new StorePresenter(this);
    }
    /**
     * 返回登录页面
     * @param view
     */
    public void back(View view) {
        startActivity(new Intent(RegActivity.this,LoginActivity.class));
    }
    /**
     * 注册成功事件
     * @param view
     */
    public void register(View view) {
        HashMap<String,String> params = new HashMap<>();
        String iphone=phone.getText().toString();
        String pass=password.getText().toString();
        params.put("phone",iphone);
        params.put("pwd",pass);
        storePresenter.getregister(params);
    }
    @Override
    public void loginsuccess(UserEntity userEntity) {

    }
    @Override
    public void loginfailure(String error) {

    }
    /**
     * 请求成功
     * @param result
     */
    @Override
    public void regsuccess(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
    }
    /**
     * 请求失败
     * @param error
     */
    @Override
    public void failure(String error) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
    }
}
