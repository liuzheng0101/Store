package com.example.mature.store.activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mature.store.R;
import com.example.mature.store.bean.UserEntity;
import com.example.mature.store.contract.StoreContract;
import com.example.mature.store.presenter.StorePresenter;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements StoreContract.IStoreView {
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.cb_eye)
    CheckBox show;
    @BindView(R.id.jz)
    CheckBox jz;
    private StorePresenter storePresenter;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initData();
        initView();
    }
    /**
     * 转化明密文
     */
    private void initView() {
        show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
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

     //记住密码
        sharedPreferences=getSharedPreferences("User",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        boolean jz1 = sharedPreferences.getBoolean("jz",false);
        if (jz1) {
            String names = sharedPreferences.getString("name",null);
            String pas = sharedPreferences.getString("passs",null);
            jz.setChecked(true);
            phone.setText(names);
            password.setText(pas);
        }
    }

    /**
     * 请求成功
     * @param userEntity
     */
    @Override
    public void loginsuccess(UserEntity userEntity) {
        Toast.makeText(LoginActivity.this,userEntity.message+"",Toast.LENGTH_SHORT).show();
        if("登录成功".equals(userEntity.message)){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }
    }
    @Override
    public void loginfailure(String error) {
        Toast.makeText(LoginActivity.this,error,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void regsuccess(String result) {

    }
    @Override
    public void failure(String error) {

    }
//登录点击事件
    public void login(View view) {
        HashMap<String,String> params=new HashMap<>();
        String iphone=phone.getText().toString();
        String pass=password.getText().toString();
        params.put("phone",iphone);
        params.put("pwd",pass);
        storePresenter.getLogin(params);
        //记住密码
        if (jz.isChecked()) {
            editor.putString("name",iphone);
            editor.putString("passs",pass);
            editor.putBoolean("jz",true);
            editor.commit();
        }
    }

    /**
     * 点击注册
     * @param view
     */
    public void register(View view) {
        startActivity(new Intent(LoginActivity.this,RegActivity.class));
    }
}
